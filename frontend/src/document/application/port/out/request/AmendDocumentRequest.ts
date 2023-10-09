type AmendDocumentRequest = {
  documentId: number,
  body: {
    title: string,
    content: string,
  },
  headers: {
    requestMemberId: number,
  },
};

export default AmendDocumentRequest;
