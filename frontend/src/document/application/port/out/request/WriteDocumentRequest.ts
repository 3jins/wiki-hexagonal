type WriteDocumentRequest = {
  body: {
    title: string,
    content: string,
  },
  headers: {
    requestMemberId: number,
  },
};

export default WriteDocumentRequest;
