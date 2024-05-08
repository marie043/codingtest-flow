# codingtest-flow
코딩테스트 제출용

#### Database 셋팅
- mariaDB사용
- 다음의 sql문 사용
``` sql
create database extension;
use extension;
create table `extensions`(
    `extension_seq` int not null auto_increment primary key,
    `value` varchar(21) not null
);
```
