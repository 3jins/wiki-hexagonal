export default class Member {
  id?: MemberId
  name: string
}

export class MemberId {
  constructor(memberId: number) {
    this.value = memberId;
  }

  value: number
}
