type WriteDocumentRequest = {
  params: {
    title: string,
    content: string,
  },
  headers: {
    requestMemberId: string,
  },
};

export default WriteDocumentRequest;
