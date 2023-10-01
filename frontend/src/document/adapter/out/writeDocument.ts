import axios from 'axios';
import WriteDocumentRequest from '@src/document/application/port/out/request/WriteDocumentRequest';
import { DOCUMENT_API_URI } from '@src/document/adapter/out/DocumentApiUri';

const writeDocument = async (request: WriteDocumentRequest): Promise<string> => {
  const baseUrl = import.meta.env.VITE_BASE_API_URL;

  const rawResponse = await axios.post(
    baseUrl + DOCUMENT_API_URI,
    request.params,
    {
      headers: request.headers,
    }
  );

  return rawResponse.data;
};

const writeDocumentMock = async (request: WriteDocumentRequest): Promise<string> =>
  Math.round(Math.random() * 1000).toString()

export default import.meta.env.MODE === 'mock' ? writeDocumentMock : writeDocument;
