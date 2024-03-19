package ssafy.navi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ssafy.navi.dto.CustomOAuth2User;
import ssafy.navi.dto.GoogleResponse;
import ssafy.navi.dto.NaverResponse;
import ssafy.navi.dto.OAuth2Response;
import ssafy.navi.entity.Role;
import ssafy.navi.entity.User;
import ssafy.navi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    // DefaultOAuth2UserService OAuth2UserService의 구현체

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User existData = userRepository.findByUsername(username);
        Role role = Role.ROLE_GUEST;

        if(existData==null) {
            User user = new User(username, oAuth2Response.getName(), oAuth2Response.getEmail(), oAuth2Response.getProfileImage(), role);

            userRepository.save(user);
        }
        else {
            existData.setUsername(username);
            existData.setNickname(oAuth2Response.getName());
            existData.setEmail(oAuth2Response.getEmail());
            existData.setImage(oAuth2Response.getProfileImage());
            role = existData.getRole();

            userRepository.save(existData);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
