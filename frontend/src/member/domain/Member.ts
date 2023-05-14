export default class Member {
  constructor(id: MemberId, name: string) {
    this.id = id;
    this.name = name;
  }

  id?: MemberId
  name: string

  static of(
    id: MemberId,
    name: string,
  ): Member {
    return new Member(
      id,
      name,
    );
  }
}

export class MemberId {
  constructor(memberId: number) {
    this.value = memberId;
  }

  value: number
}
