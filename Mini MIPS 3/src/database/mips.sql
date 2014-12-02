
drop table if exists jtype;
create table jtype (
	id integer primary key autoincrement,
	name text not null,
	opcode integer not null
);

drop table if exists rtype;
create table rtype (
	id integer primary key autoincrement,
	name text not null,
	opcode integer not null,
	func integer not null
);

drop table if exists itype;
create table itype (
	id integer primary key autoincrement,
	name text not null,
	opcode integer not null
);


drop table if exists command;
create table command (
	cmdid integer primary key autoincrement,
	instruction text,
	memloc integer,
	error boolean,
	message text,
	opcode text
);

drop table if exists label;
create table label (
	labid integer primary key autoincrement,
	name text,
	memloc integer
);

drop table if exists pipeline;
create table pipeline (
	pipid integer primary key autoincrement,
	instnum integer not null,
	ifc boolean,
	idc boolean,
	exc boolean,
	memc boolean,
	wbc boolean
);

drop table if exists register;
create table register (
	regid integer primary key autoincrement,
	name text not null,
	value integer
);

insert into jtype values(0, 'J', 2);

insert into rtype values(0, 'DSUBU', 0, 47);
insert into rtype values(1, 'DDIV', 0, 30);
insert into rtype values(2, 'MFHI', 0, 16);
insert into rtype values(3, 'MFLO', 0, 18);
insert into rtype values(4, 'AND', 0, 36);
insert into rtype values(5, 'DSRLV', 0, 22);
insert into rtype values(6, 'SLT', 0, 42);

insert into itype values(0, 'BNEZ', 5);
insert into itype values(1, 'LW', 35);
insert into itype values(2, 'LWU', 39);
insert into itype values(3, 'LD', 55);
insert into itype values(4, 'SW', 43);
insert into itype values(5, 'SD', 63);
insert into itype values(6, 'DADDIU', 25);
insert into itype values(7, 'ORI', 13);

insert into register values(0, 'R0', 0);
insert into register values(1, 'R1', 0);
insert into register values(2, 'R2', 0);
insert into register values(3, 'R3', 0);
insert into register values(4, 'R4', 0);
insert into register values(5, 'R5', 0);
insert into register values(6, 'R6', 0);
insert into register values(7, 'R7', 0);
insert into register values(8, 'R8', 0);
insert into register values(9, 'R9', 0);
insert into register values(10, 'R10', 0);
insert into register values(11, 'R11', 0);
insert into register values(12, 'R12', 0);
insert into register values(13, 'R13', 0);
insert into register values(14, 'R14', 0);
insert into register values(15, 'R15', 0);
insert into register values(16, 'R16', 0);
insert into register values(17, 'R17', 0);
insert into register values(18, 'R18', 0);
insert into register values(19, 'R19', 0);
insert into register values(20, 'R20', 0);
insert into register values(21, 'R21', 0);
insert into register values(22, 'R22', 0);
insert into register values(23, 'R23', 0);
insert into register values(24, 'R24', 0);
insert into register values(25, 'R25', 0);
insert into register values(26, 'R26', 0);
insert into register values(27, 'R27', 0);
insert into register values(28, 'R28', 0);
insert into register values(29, 'R29', 0);
insert into register values(30, 'R30', 0);
insert into register values(31, 'R31', 0);

