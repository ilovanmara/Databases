drop database if exists proiect;
create database proiect;

set foreign_key_checks = 0;
use proiect;

create table utilizator(
id int not null auto_increment primary key,
CNP varchar(14),
nume varchar(30),
prenume varchar(30),
adresa varchar(60),
nr_telefon varchar(10),
email varchar(40),
IBAN varchar(50),
nr_contract INT default 0,
parola varchar(40)
);

create table administrator(
idAdmin int not null primary key,
constraint fk_id foreign key (idAdmin) references utilizator (id) on delete cascade on update cascade
);

create table superadministrator(
idSuperAdmin int not null primary key,
constraint fk_id1 foreign key (idSuperAdmin) references utilizator (id) on delete cascade on update cascade
);

create table student(
idStudent int not null primary key,
an_studiu int,
nr_ore int default 0,
constraint fk_id2 foreign key (idStudent) references utilizator (id) on delete cascade on update cascade
);

create table materie(
idMaterie int not null auto_increment primary key,
nume_materie varchar(40) not null unique,
descriere text,
data_inceput date,
data_sfarsit date
);

create table catalog(
idStudent int not null,
idMaterie int not null,
nume_materie varchar(30),
nota_lab float(4,2) default 0,
nota_seminar float(4,2) default 0,
nota_curs float(4,2) default 0,
nota_finala float(4,2) default 0,
primary key(idStudent, idMaterie),
constraint fk_id6 foreign key (idStudent) references student (idStudent) on delete cascade on update cascade,
constraint fk_id7 foreign key (idMaterie) references materie (idMaterie) on delete cascade on update cascade
);

create table profesor(
idProfesor int not null primary key,
cursuri_predate int default 0,
nr_min_ore int default 0,
nr_ore_predate int default 0,
nr_max_ore int default 0,
departament varchar(30),
constraint fk_id3 foreign key (idProfesor) references utilizator (id) on delete cascade on update cascade
);

create table distributie_materie(
idProfesor int not null,
idMaterie int not null,
idStructura int not null,
primary key(idProfesor, idMaterie, idStructura),
constraint fk_id4 foreign key (idProfesor) references profesor (idProfesor) on delete cascade on update cascade,
constraint fk_id5 foreign key (idMaterie) references materie (idMaterie) on delete cascade on update cascade
);

create table structura_materie(
idStructura int not null auto_increment primary key,
nume_materie varchar(40),
tip_activitate varchar(15),
nr_studenti int default 0,
nr_max_studenti int default 0,
procentaj int default 0,
zi varchar(10) default null,
ora time default 0,
durata int default 0,
constraint fk_nume_materie1 foreign key (nume_materie) references materie (nume_materie) on delete cascade on update cascade
);

create table inscriere_la_activitati(
idStudent int not null,
idMaterie int not null,
idStructura int not null,
primary key(idStudent, idMaterie, idStructura),
constraint fk_id8 foreign key (idStudent) references student (idStudent) on delete cascade on update cascade,
constraint fk_id9 foreign key (idMaterie) references materie (idMaterie) on delete cascade on update cascade
);

create table grup_studiu(
idGrup int not null auto_increment primary key,
nume_materie varchar(40),
nr_min_participanti int,
nr_participanti int default 0,
data_desfasurare date,
ora time,
durata int,
constraint fk_nume_materie2 foreign key (nume_materie) references materie (nume_materie) on delete cascade on update cascade
);

create table inscriere_grup_studiu(
idStudent int not null,
idGrup int not null,
primary key(idStudent, idGrup),
constraint fk_id10 foreign key (idStudent) references student (idStudent) on delete cascade on update cascade,
constraint fk_id11 foreign key (idGrup) references grup_studiu (idGrup) on delete cascade on update cascade
);

create table predare_grup_studiu(
idProfesor int not null,
idGrup int not null,
primary key(idProfesor, idGrup),
constraint fk_id12 foreign key (idProfesor) references profesor (idProfesor) on delete cascade on update cascade,
constraint fk_id13 foreign key (idGrup) references grup_studiu (idGrup) on delete cascade on update cascade
);

create table mesaje_grup(
idStudent int not null,
data_mesaj datetime not null,
idGrup int not null,
mesaj varchar(255),
primary key(idStudent, data_mesaj),
constraint fk_id14 foreign key (idStudent) references student (idStudent) on delete cascade on update cascade,
constraint fk_id15 foreign key (idGrup) references grup_studiu (idGrup) on delete cascade on update cascade
);

delimiter //
CREATE TRIGGER generare_nr_contract BEFORE INSERT ON utilizator 
FOR EACH ROW BEGIN 
         declare i,j int;
         set i=100;
         set j=2000;
         set @nr=FLOOR(i + RAND() * (j - i + 1));
         set new.nr_contract=@nr;
        END; 
//

drop procedure if exists inserare_utilizator;
delimiter //
create procedure inserare_utilizator(CNPp varchar(14), numep varchar(30), prenumep varchar(30), adresap varchar(60), nrtelefonp varchar(10), emailp varchar(40), ibanp varchar(50), parolap varchar(40))
begin
	insert into utilizator(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, parola) values (CNPp, numep, prenumep, adresap, nrtelefonp, emailp, ibanp, parolap);
end //
delimiter ;

drop procedure if exists inserare_student;
delimiter //
create procedure inserare_student(idstudent int, an_studiup int)
begin
	insert into student (idStudent, an_studiu) values (idstudent, an_studiup);
end//
delimiter ;

drop procedure if exists inserare_administrator;
delimiter //
create procedure inserare_administrator(idAdmin int)
begin
	insert into administrator values (idAdmin);
end//
delimiter ;

drop procedure if exists inserare_superadministrator;
delimiter //
create procedure inserare_superadministrator(idAdmin int)
begin
	insert into superadministrator values (idAdmin);
end//
delimiter ;

drop procedure if exists inserare_profesor;
delimiter //
create procedure inserare_profesor(idprof int, departament varchar(30))
begin
	insert into profesor (idProfesor, departament) values (idprof, departament);
end//
delimiter ;

drop procedure if exists asignare_profesor;
delimiter //
create procedure asignare_profesor(idprof int, idmaterie int, curs boolean, seminar boolean, lab boolean, nume varchar(40))
begin
    declare idStruct int;
    update profesor set cursuri_predate = cursuri_predate + 1 where idProfesor = idprof;
    if(curs) then
		insert into structura_materie (nume_materie, tip_activitate) values (nume, "curs");
        select MAX(idStructura) into idStruct from structura_materie;
		insert into distributie_materie values (idprof, idmaterie, idStruct);
	end if;
	if(seminar) then
		insert into structura_materie (nume_materie, tip_activitate) values (nume, "seminar");
		select MAX(idStructura) into idStruct from structura_materie;
		insert into distributie_materie values (idprof, idmaterie, idStruct);
	end if;
	if(lab) then
		insert into structura_materie (nume_materie, tip_activitate) values (nume, "laborator");
        select MAX(idStructura) into idStruct from structura_materie;
		insert into distributie_materie values (idprof, idmaterie, idStruct);
	end if;
end //
delimiter ;


drop procedure if exists inserare_materie;
delimiter //
create procedure inserare_materie(nume_materie VARCHAR(40), descriere text, data_inceput date, data_sfarsit date)
begin
	insert into materie(nume_materie, descriere, data_inceput, data_sfarsit) values (nume_materie, descriere, data_inceput, data_sfarsit);
end //
delimiter ;

drop procedure if exists update_utilizator;
delimiter //
create procedure update_utilizator(idp int, CNPp varchar(14), numep varchar(30), prenumep varchar(30), adresap varchar(60), nrtelefonp varchar(10), emailp varchar(40), ibanp varchar(50), nr_contractp int)
begin
	update utilizator
    set CNP = CNPp, nume = numep, prenume = prenumep, adresa = adresap, nr_telefon = nrtelefonp, email = emailp, IBAN = ibanp, nr_contract = nr_contractp
    where id = idp;
end //
delimiter ;

drop procedure if exists update_student;
delimiter //
create procedure update_student(idp int, an_studiup int)
begin
	update student
    set an_studiu = an_studiup
    where idStudent = idp;
end //
delimiter ;

drop procedure if exists update_profesor;
delimiter //
create procedure update_profesor(idp int, departamentp varchar(30), nr_min_orep int, nr_max_orep int)
begin
	update profesor
    set departament = departamentp, nr_min_ore = nr_min_orep, nr_max_ore = nr_max_orep
    where idProfesor = idp;
end //
delimiter ;

drop procedure if exists updatare_structura_materie;
delimiter //
create procedure updatare_structura_materie(idProfp int, idStructp int, procentajp int, nrmaxp int, zip varchar(10), orap time, duratap int)
begin
	declare ore int;
    set ore = 0;
    select durata into ore from structura_materie where idStructura = idStructp;
	update structura_materie set procentaj = procentajp, nr_max_studenti = nrmaxp, zi = zip, ora = orap, durata = duratap where idStructura = idStructp;
    update profesor set nr_ore_predate = nr_ore_predate - ore + duratap where idProfesor = idProfp;
end //
delimiter ;

drop procedure if exists inscriere_studenti;
delimiter //
create procedure inscriere_studenti(idStudentp int, idMateriep int, idStructurap int)
begin
	declare m varchar(30);
    select nume_materie into m from materie where idMaterie = idMateriep;
	insert into inscriere_la_activitati (idStudent, idMaterie, idStructura) values (idStudentp, idMateriep, idStructurap);
    if((select idStudent from catalog where idStudent = idStudentp and idMaterie = idMateriep) is null) then
		insert into catalog (idStudent, idMaterie, nume_materie) values (idStudentp, idMateriep, m);
	end if;
	update student set nr_ore = nr_ore + (select durata from structura_materie where idStructura = idStructurap) where idStudent = idStudentp;
	update structura_materie set nr_studenti = nr_studenti + 1 where idStructura = idStructurap;
        
end //
delimiter ;

drop procedure if exists renuntare_studenti;
delimiter //
create procedure renuntare_studenti(idStudentp int, idMateriep int)
begin
	delete from inscriere_la_activitati where idStudent = idStudentp and idMaterie = idMateriep;
	delete from catalog where idStudent = idStudentp and idMaterie = idMateriep;
end //
delimiter ;

drop procedure if exists reactualizare_structura;
delimiter //
create procedure reactualizare_structura(idStudentp int, idStructurap int)
begin
	update student set nr_ore = nr_ore - (select durata from structura_materie where idStructura = idStructurap) where idStudent = idStudentp;
	update structura_materie set nr_studenti = nr_studenti - 1 where idStructura = idStructurap;
end //
delimiter ;

drop procedure if exists updatare_catalog;
delimiter //
create procedure updatare_catalog(idStudentp int, idMateriep int, nota float, activitate varchar(15))
begin
	if(activitate = "curs") then
		update catalog set nota_curs = nota where idMaterie = idMateriep and idStudent = idStudentp;
	end if;
    if(activitate = "seminar") then
		update catalog set nota_seminar = nota where idMaterie = idMateriep and idStudent = idStudentp;
	end if;
    if(activitate = "laborator") then
		update catalog set nota_lab = nota where idMaterie = idMateriep and idStudent = idStudentp;
	end if;
end //
delimiter ;

drop procedure if exists notafinala;
delimiter //
create procedure notafinala(idStudentp int, idMateriep int, procentcurs int, procentsem int, procentlab int)
begin
	update catalog set nota_finala = (nota_curs * procentcurs) / 100 + (nota_seminar * procentsem) / 100 + (nota_lab * procentlab) / 100 where idStudent = idStudentp and idMaterie = idMateriep;
end //
delimiter ;

drop procedure if exists creare_activitate;
delimiter //
create procedure creare_activitate(idStudentp int, nume_materiep varchar(40), nr_min_p int, datap date, orap time, duratap int)
begin
	declare idgr int;
	insert into grup_studiu (nume_materie, nr_min_participanti, data_desfasurare, ora, durata) values (nume_materiep, nr_min_p, datap, orap, duratap);
    select idGrup into idgr from grup_studiu where nume_materie = nume_materiep;
    insert into inscriere_grup_studiu (idStudent, idGrup) values (idStudentp, idgr);
    update grup_studiu set nr_participanti = nr_participanti + 1 where idGrup = idgr;
end //
delimiter ;

drop procedure if exists inscriere_activitate_grup;
delimiter //
create procedure inscriere_activitate_grup(idStudentp int, nume_materiep varchar(40))
begin
	declare idgr int;
    select idGrup into idgr from grup_studiu where nume_materie = nume_materiep;
    insert into inscriere_grup_studiu (idStudent, idGrup) values (idStudentp, idgr);
    update grup_studiu set nr_participanti = nr_participanti + 1 where idGrup = idgr;
end //
delimiter ;

drop procedure if exists renuntare_activitate_grup;
delimiter //
create procedure renuntare_activitate_grup(idStudentp int, nume_materiep varchar(40))
begin
	declare idgr int;
    select idGrup into idgr from grup_studiu where nume_materie = nume_materiep;
    delete from inscriere_grup_studiu where idStudent = idStudentp and idGrup = idgr;
    update grup_studiu set nr_participanti = nr_participanti - 1 where idGrup = idgr;
end //
delimiter ;

drop procedure if exists predare_grup;
delimiter //
create procedure predare_grup(idProfesorp int, nume_materiep varchar(40))
begin
	declare idgr int;
    select idGrup into idgr from grup_studiu where nume_materie = nume_materiep;
	insert into predare_grup_studiu (idProfesor, idGrup) values (idProfesorp, idgr);
end //
delimiter ;

drop procedure if exists trimitere_mesaj;
delimiter //
create procedure trimitere_mesaj(idStudent int, data_mesaj datetime, idGrup int, mesaj varchar(255))
begin
	insert into mesaje_grup(idStudent, data_mesaj, idGrup, mesaj) values (idStudent, data_mesaj, idGrup, mesaj);
end //
delimiter ;

#inserare utilizatori

#Super-administrator
call inserare_utilizator("8732874238234", "Achim", "Carlos", "Cluj, Str. Vanatorilor, nr. 71", "0782513252", "ack.carlos@yahoo.com", "BTRO987UI886TT65", "Carlito21");
call inserare_superadministrator((select id from utilizator where email = "ack.carlos@yahoo.com"));

#Administratori
call inserare_utilizator("7832654863246", "Comanescu", "Pedro", "Cluj, Str. Gladiolelor, nr. 14", "0767098125", "comanescu_pedro@yahoo.com", "BTRO62357843DT56Y3", "PedroC4");
call inserare_utilizator("2137986437893", "Banciu", "Alina", "Cluj, Str. Toporasilor, nr. 55", "0709872138", "alina_ba@gmail.com", "BTRO983267894GH75T4354", "Alinutza02Bc");
call inserare_utilizator("9018638723642", "Miron", "Costin", "Cluj, Str. Victor Babes, nr. 112", "0756889022", "costi.m66@gmail.com", "BTRO734654553264", "CostiM15243");
call inserare_administrator((select id from utilizator where email = "comanescu_pedro@yahoo.com"));
call inserare_administrator((select id from utilizator where email = "alina_ba@gmail.com"));
call inserare_administrator((select id from utilizator where email = "costi.m66@gmail.com"));

#Profesori
call inserare_utilizator("2563274203713", "Albu", "Sorana", "Targu Neamt str. Albinelor, nr. 87", "0701271234", "albu_sorana@yahoo.com", "BTRO0384765934", "Sori123");
call inserare_utilizator("5184732847947", "Stan", "Alin", "Arad Str. Toamnei, nr. 155", "0723888346", "stan_alin@yahoo.com", "BTRO0384765666", "Alin123");
call inserare_utilizator("3928342748623", "Turcas", "Madalina", "Timisoara Str. Plevnei, nr. 2", "0772364211", "turc_madal1a@gmail.com", "BTRO0816729324", "Malina3");
call inserare_utilizator("1029635274363", "Popovici", "Florian", "Satu Mare, Str. Vaida Voievod, nr. 114", "0746998207", "florian03@yahoo.com", "BTRO278354648", "PopFlor14n");
call inserare_utilizator("5184732747892", "Lungu", "Catalin", "Targu Mures Str. 7 Noiembrie, nr. 234", "0755088346", "lungu_catalin@yahoo.com", "BTRO0380765776", "Catalin123");
call inserare_utilizator("3928342748877", "Ionescu", "Paula", "Cluj Napoca Bulevardul 21 Decembrie, nr. 9", "0779964811", "ionescu_paula@gmail.com", "BTRO0816729004", "Paula123");
call inserare_profesor((select id from utilizator where email = "lungu_catalin@yahoo.com"), "Electronica");
call inserare_profesor((select id from utilizator where email = "ionescu_paula@gmail.com"), "Tehnologie");
call inserare_profesor((select id from utilizator where email = "florian03@yahoo.com"), "Automatizari");
call inserare_profesor((select id from utilizator where email = "albu_sorana@yahoo.com"), "Calculatoare");
call inserare_profesor((select id from utilizator where email = "stan_alin@yahoo.com"), "Constructii");
call inserare_profesor((select id from utilizator where email = "turc_madal1a@gmail.com"), "Instalatii");
update profesor set nr_min_ore = 2, nr_max_ore = 20 where idProfesor = 5;
update profesor set nr_min_ore = 2, nr_max_ore = 18 where idProfesor = 6;
update profesor set nr_min_ore = 2, nr_max_ore = 22 where idProfesor = 7;
update profesor set nr_min_ore = 2, nr_max_ore = 20 where idProfesor = 8;
update profesor set nr_min_ore = 2, nr_max_ore = 22 where idProfesor = 9;
update profesor set nr_min_ore = 2, nr_max_ore = 22 where idProfesor = 10;

#Studentiactor_info
call inserare_utilizator("2493485896123", "Banciu", "Matei", "Targu Jiu, Str. bd. Transilvaniei, nr. 34", "0723672345", "matei.b43@yahoo.com", "BTRO384765934", "Matei123");
call inserare_utilizator("2864283789437", "Corches", "Adina", "Cluj-Napoca, Str. Victor Babes, nr. 55", "0746583221", "adina73@gmail.com", "BCRRO980239TF32", "AdinaC17");
call inserare_utilizator("3718723674673", "Dobrescu", "Alin", "Targu Mures, Str. Lalelelor, nr 86", "0721753627", "dbr_alin20feb@gmail.com", "RFZRO543626UU6236", "Dobre44");
call inserare_utilizator("7621672565256", "Turcas", "Madalina", "Satu Mare, Str. bd. Republicii, nr 123", "0762739981", "mada_turcas@gmail.com", "BTRO76251763TY43", "Mada16");
call inserare_utilizator("8276186432276", "Faur", "Andrei", "Craiova, Str. Eroilor, nr 2", "0733567810", "andrei_f16@yahoo.com", "BTROU42638278", "Andr3w12");
call inserare_utilizator("6178236432349", "Mior", "Cristian", "Cugir, Str. Gladiolelor, nr. 17", "0737126428", "cristi04mi.or@yahoo.com", "BRDRO343748FST23", "Miorita44");
call inserare_utilizator("9271483284737", "Marcu", "Stefan", "Alba Iulia, Str. Stefan cel Mare, nr 34", "0765229034", "stefan.albert@gmail.com", "BCRRO90420382", "Steph13");
call inserare_utilizator("0198477235628", "Stanescu", "Georgia", "Baia Mare, Str. Andronescu, nr 67", "0756710926", "stanescug6@yahoo.com", "BRDRO9812387321", "Gia88");
call inserare_utilizator("3628209236527", "Crisan", "Ana", "Iasi, Str. Dorobantilor, nr.57", "0745902118", "ana_crs88@gmail.com", "RFZRO0321943FT4", "Ana1999");
call inserare_utilizator("8279430129389", "Zoltan", "Florin", "Suceava, Str. Merilor, nr. 101", "044759112", "zolt_florin@gmail.com", "INGRO46348732", "ZoltFlorin03");
call inserare_utilizator("9273923042338", "Bucur", "Critina", "Cluj-Napoca, Str. Aurel Vlaicu, nr 30", "0798203441", "criss_bucur1@yahoo.com", "BTRO918273YT56", "Cr1st1na#");
call inserare_utilizator("3781928174732", "Coste", "Calin", "Botosani, Str. Matei Corvin, nr. 22", "0737445098", "calin_ccc24@gmail.com", "BRDRO98329544454", "CosteCal1n");
call inserare_utilizator("7365783716349", "Ciucur", "Radu", "Oradea, Str. Soarelui, nr. 88", "0792387437", "raducu_c12@yahoo.com", "INGRO9328428374", "Ciucurel44");
call inserare_utilizator("9857398574243", "Rotoi", "Felicia", "Turda, Str. Regele Ferdinand, nr. 8", "0776253997", "feli_r0t0i@yahoo.com", "BTRO72641873264", "Feliii98");
call inserare_utilizator("2493485896123", "Graur", "Stelian", "Pitesti, Str. Lalelelor, nr. 14", "0756722177", "stelian_gr@gmail.com", "BTRO3847628431", "Steli45Grau$");
call inserare_student((select id from utilizator where email = "matei.b43@yahoo.com"), 2);
call inserare_student((select id from utilizator where email = "adina73@gmail.com"), 1);
call inserare_student((select id from utilizator where email = "dbr_alin20feb@gmail.com"), 1);
call inserare_student((select id from utilizator where email = "mada_turcas@gmail.com"), 3);
call inserare_student((select id from utilizator where email = "andrei_f16@yahoo.com"), 1);
call inserare_student((select id from utilizator where email = "cristi04mi.or@yahoo.com"), 4);
call inserare_student((select id from utilizator where email = "stefan.albert@gmail.com"), 3);
call inserare_student((select id from utilizator where email = "stanescug6@yahoo.com"), 1);
call inserare_student((select id from utilizator where email = "ana_crs88@gmail.com"), 2);
call inserare_student((select id from utilizator where email = "zolt_florin@gmail.com"), 4);
call inserare_student((select id from utilizator where email = "criss_bucur1@yahoo.com"), 2);
call inserare_student((select id from utilizator where email = "calin_ccc24@gmail.com"), 2);
call inserare_student((select id from utilizator where email = "raducu_c12@yahoo.com"), 1);
call inserare_student((select id from utilizator where email = "feli_r0t0i@yahoo.com"), 4);
call inserare_student((select id from utilizator where email = "stelian_gr@gmail.com"), 3);

#inserare materii

call inserare_materie("PC", "Aceasta materie ne va initia in limbaju C", "2022-09-02", "2023-02-01");
call inserare_materie("OOP", "Programare orientata pe obiect", "2022-09-02", "2023-02-01");
call inserare_materie("AF", "Algoritmica si statistica complexitatilor", "2022-09-02", "2023-02-01");
call inserare_materie("AM", "Analiza Matematica", "2022-09-02", "2023-02-01");
call inserare_materie("CAN", "Circuite Analogice si Numerice", "2022-09-02", "2023-02-01");
call inserare_materie("IA", "Inteligenta Artificiala", "2022-09-02", "2023-02-01");
call inserare_materie("PL", "Proiectare Logica", "2022-09-02", "2023-02-01");
call inserare_materie("ED", "Electronica Digitala", "2022-09-02", "2023-02-01");

#asignare profesori

call asignare_profesor(5, 1, true, false, false, "PC");
call asignare_profesor(7, 1, false, false, true, "PC");
call asignare_profesor(8, 1, false, true, true, "PC");
call asignare_profesor(6, 2, true, false, false, "OOP");
call asignare_profesor(7, 2, false, true, true, "OOP");
call asignare_profesor(9, 3, true, true, true, "AF");
call asignare_profesor(10, 3, false, false, true, "AF");
call asignare_profesor(7, 4, true, true, true, "AM");

#editare date despere materii

call updatare_structura_materie(5, 1, 50, 30, "marti", "12:00", 2);
call updatare_structura_materie(7, 2, 30, 20, "luni", "10:00", 2);
call updatare_structura_materie(8, 3, 20, 25, "vineri", "14:00", 2);
call updatare_structura_materie(8, 4, 30, 20, "miercuri", "08:00", 2);
call updatare_structura_materie(6, 5, 60, 40, "vineri", "10:00", 2);
call updatare_structura_materie(7, 6, 15, 23, "luni", "12:00", 2);
call updatare_structura_materie(7, 7, 25, 18, "marti", "08:00", 2);
call updatare_structura_materie(9, 8, 70, 35, "miercuri", "10:00", 2);
call updatare_structura_materie(9, 9, 10, 25, "joi", "10:00", 2);
call updatare_structura_materie(9, 10, 20, 20, "joi", "14:00", 2);
call updatare_structura_materie(10, 11, 20, 20, "luni", "08:00", 2);
call updatare_structura_materie(7, 12, 50, 45, "vineri", "12:00", 2);
call updatare_structura_materie(7, 13, 25, 32, "marti", "10:00", 2);
call updatare_structura_materie(7, 14, 25, 17, "joi", "08:00", 2);

#inscriere studenti la cursuri

call inscriere_studenti(11, 1, 1);
call inscriere_studenti(11, 1, 2);
call inscriere_studenti(11, 1, 3);
call inscriere_studenti(11, 3, 8);
call inscriere_studenti(11, 3, 9);
call inscriere_studenti(11, 3, 11);
call inscriere_studenti(12, 1, 1);
call inscriere_studenti(12, 1, 2);
call inscriere_studenti(12, 1, 3);
call inscriere_studenti(12, 2, 5);
call inscriere_studenti(12, 2, 6);
call inscriere_studenti(12, 2, 7);
call inscriere_studenti(13, 2, 5);
call inscriere_studenti(13, 2, 6);
call inscriere_studenti(13, 2, 7);
call inscriere_studenti(13, 4, 12);
call inscriere_studenti(13, 4, 13);
call inscriere_studenti(13, 4, 14);
call inscriere_studenti(14, 3, 8);
call inscriere_studenti(14, 3, 9);
call inscriere_studenti(14, 3, 10);

update catalog set nota_curs = 8.0, nota_seminar = 9.5, nota_lab = 10.0 where idStudent = 11 and idMaterie = 1;
update catalog set nota_curs = 7.0, nota_seminar = 9.0, nota_lab = 8.5 where idStudent = 11 and idMaterie = 3;
update catalog set nota_curs = 8.5, nota_seminar = 9.0, nota_lab = 9.5 where idStudent = 12 and idMaterie = 1;
update catalog set nota_curs = 8.0, nota_seminar = 9.0, nota_lab = 8.5 where idStudent = 12 and idMaterie = 2;
update catalog set nota_curs = 9.0, nota_seminar = 9.5, nota_lab = 10.0 where idStudent = 13 and idMaterie = 2;
update catalog set nota_curs = 9.5, nota_seminar = 10.0, nota_lab = 8.5 where idStudent = 13 and idMaterie = 4;
update catalog set nota_curs = 9.0, nota_seminar = 8.0, nota_lab = 10.0 where idStudent = 14 and idMaterie = 3;
call notafinala(11, 1, 50, 20, 30);
call notafinala(11, 3, 70, 10, 20);
call notafinala(12, 1, 50, 20, 30);
call notafinala(12, 2, 60, 15, 25);
call notafinala(13, 2, 60, 15, 25);
call notafinala(13, 4, 50, 25, 25);
call notafinala(14, 3, 70, 10, 20);