CREATE TABLE "SWBOARD"(
  "IDX" NUMBER(*,0) NOT NULL, 
	"NAME" CHAR(20 BYTE) NOT NULL, 
	"SUBJECT" VARCHAR2(200 BYTE) NOT NULL, 
	"CONTENT" VARCHAR2(2000 BYTE) NOT NULL, 
	"REF" NUMBER(*,0), 
	"LEV" NUMBER(*,0), 
	"SEQ" NUMBER(*,0), 
	"HIT" NUMBER(*,0) DEFAULT 0, 
	"WRITEDATE" TIMESTAMP (6) DEFAULT sysdate, 
	PRIMARY KEY ("IDX")
);

delete from swboard;
drop sequence swboard_idx_seq;
create sequence swboard_idx_seq;
commit;

select * from swboard order by ref desc, seq asc;
select count(*) from swboard;

insert into swboard (idx, name, subject, content, ref, lev, seq) 
values (swboard_idx_seq.nextval, 'ȫ�浿', '1��', '1�� �Դϴ�.', swboard_idx_seq.currval, 0, 0);
insert into swfreeboard (idx, name, subject, content, ref, lev, seq) 
values (swboard_idx_seq.nextval, '�Ӳ���', '2��', '2�� �Դϴ�.', swboard_idx_seq.currval, 0, 0);
insert into swboard (idx, name, subject, content, ref, lev, seq) 
values (swboard_idx_seq.nextval, '����', '3��', '3�� �Դϴ�.', swboard_idx_seq.currval, 0, 0);
insert into swboard (idx, name, subject, content, ref, lev, seq) 
values (swboard_idx_seq.nextval, '������', '4��', '4�� �Դϴ�.', swboard_idx_seq.currval, 0, 0);