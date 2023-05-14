import Member, { MemberId } from '@src/member/domain/Member';
import { faker } from '@faker-js/faker';

export default () => Member.of(
  new MemberId(Number(faker.random.numeric())),
  faker.random.words(),
);
