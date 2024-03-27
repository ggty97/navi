import type { CoverList } from './types';
import { baseApi } from '@/shared/api';
import axios, { AxiosResponse } from 'axios';

export const hotCoverListApi = async (): Promise<CoverList | null> => {
  try {
    const response: AxiosResponse<CoverList> = await axios.get(
      `${baseApi}/main/covers/hot`,
    );
    return response.data;
  } catch (error) {
    console.error('Error get hot cover list', error);
    return null;
  }
};
