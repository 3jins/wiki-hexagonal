export default class Member {
  constructor(
    id: MemberId,
    name: string,
  ) {
    this.id = id;
    this.name = name;
  }

  id?: MemberId
  name: string
}

export class MemberId {
  constructor(memberId: number) {
    this.value = memberId;
  }

  value: number
}
