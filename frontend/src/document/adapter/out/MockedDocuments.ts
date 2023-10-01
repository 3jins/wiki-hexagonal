import DocumentStatus from '@src/document/domain/DocumentStatus';

export default [
  {
    documentId: 11,
    author: {
      memberId: 5,
      name: '용용',
    },
    status: DocumentStatus.ON_DISPLAY,
    latestVersionId: 4,
    title: '빙그레 verse 1',
    content: '<p>눈앞에 널린 바다</p>' +
      '<p>만끽할 수 없어 왜</p>' +
      '<p>충분히 나는 예쁜데</p>' +
      '<p>다른 남자 만나지 못해</p>' +
      '<p>자유롭고 싶어 하는 돌고래처럼</p>' +
      '<p>수조 안에 갇혀서</p>' +
      '<p>너의 어장 안에 사는 게</p>' +
      '<p>이건 마치 아쿠아맨</p>' +
      '<p>어떻게든 너를 내게 돌려내</p>' +
      '<p>돌고 돌아서라도 오길 바라네</p>' +
      '<p>그 모습이 초라해도</p>' +
      '<p>숨 못 쉬게 위험해도</p>' +
      '<p>계속 돌아가는 걸 무한대로 네 앞에</p>' +
      '<p>1800km 하루에</p>' +
      '<p>심장은 헤엄쳐</p>' +
      '<p>계속 발버둥 치는</p>' +
      '<p>부레 없는 돌고래처럼</p>' +
      '<p>어릴 적에 내가 불쌍하다 여겼던</p>' +
      '<p>Dolphin 나를 보고 비웃는 듯해</p>',
    createdAt: new Date('2023-03-26T15:00:00'),
    updatedAt: new Date('2023-03-27T17:00:00'),
  },
  {
    documentId: 12,
    author: {
      memberId: 6,
      name: '디핵',
    },
    status: DocumentStatus.ON_DISPLAY,
    latestVersionId: 2,
    title: '빙그레 verse 2',
    content: '<p>빙글 뱅글 난</p>' +
      '<p>너의 주위만</p>' +
      '<p>맴돌았던 기억이 나</p>' +
      '<p>언제쯤에야</p>' +
      '<p>너의 눈에 난</p>' +
      '<p>저장되는 걸까</p>' +
      '<p>이제 잔상조차 안 남은</p>' +
      '<p>밤이 오면</p>' +
      '<p>낡아빠진 아픔과</p>' +
      '<p>마주 앉아서</p>' +
      '<p>아프고 많이 아팠다고</p>' +
      '<p>혼잣말하다가</p>' +
      '<p>잠에 들어버릴 것만 같아</p>',
    createdAt: new Date('2023-03-27T15:00:00'),
    updatedAt: new Date('2023-03-27T15:00:00'),
  }
];
