import axios from 'axios';
import AmendDocumentRequest from '@src/document/application/port/out/request/AmendDocumentRequest';
import { DOCUMENT_API_URI } from '@src/document/adapter/out/DocumentApiUri';

const amendDocument = async (request: AmendDocumentRequest): Promise<string> => {
  const baseUrl = import.meta.env.VITE_BASE_API_URL;

  const rawResponse = await axios.patch(
    `${baseUrl}${DOCUMENT_API_URI}/${request.documentId}`,
    request.body,
    {
      headers: request.headers,
    }
  );

  return rawResponse.data;
};

const amendDocumentMock = async (request: AmendDocumentRequest): Promise<string> =>
  Math.round(Math.random() * 1000).toString()

export default import.meta.env.MODE === 'mock' ? amendDocumentMock : amendDocument;
