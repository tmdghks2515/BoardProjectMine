create table hiver_member(
	id varchar2(15 byte) primary key,
	pass varchar2(30 byte) not null,
	name varchar2(30 byte),
	age number(3),
	grade number(1)
);

insert into hiver_member values('tmdghks2515','123456','임승환',25,1);
SELECT ID,NAME,AGE,GRADE_NAME FROM HIVER_MEMBER,GRADE_LIST WHERE GRADE=GRADE_NO AND ID LIKE 'tmdghks2515';
select * from HIVER_MEMBER;