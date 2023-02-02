
alter session 	set "_oracle_script"=true;
create user princess identified by 0629;
grant connect, resource, unlimited tablespace to princess;
conn princess/0629;

--drop user princess CASCADE;