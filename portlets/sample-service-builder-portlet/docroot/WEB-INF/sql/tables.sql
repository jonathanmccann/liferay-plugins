create table SSB_Bar (
	barId LONG not null primary key
);

create table SSB_Foo (
	uuid_ VARCHAR(75) null,
	fooId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	field1 VARCHAR(75) null,
	field2 BOOLEAN,
	field3 INTEGER,
	field4 DATE null,
	field5 VARCHAR(75) null
);

create table SSB_Foos_Bars (
	barId LONG not null,
	fooId LONG not null,
	primary key (barId, fooId)
);