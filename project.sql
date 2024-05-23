drop table Transac
/
drop table RDV
/
drop table AgentBiens
/
drop table Demande
/
drop table biensImmobiliers
/
drop table Agent
/
drop table Client
/
Create table Client(NumClt number,
                    Nom varchar2(20) not NULL,
                    Prenom varchar2(20) not NULL,
                    numerotel varchar(10) unique,
                    email varchar2(40) unique,
                    typeClt varchar2(15) not NULL,
                    constraint PK_Client primary key (NumClt),
                    constraint verif_typeclt check (typeClt in ('acheteur', 'locataire', 'vendeur' , 'bailleur')))
                    /

Create table Agent(NumAgt number,
                   Nom varchar2(20) not NULL,
                   Prenom varchar2(20)not NULL,
                   constraint PK_Agent primary key (NumAgt))
                   /
Create table biensImmobiliers(NumBiens number,
                              TypeBiens varchar(5) not NULL,
                              localisation varchar2(15) not NULL,
                              PrixVent number not NULL,
                              prixLocation number not NULL,
                              taille number not NULL,
                              NbrCh number not NULL,
                              constraint PK_biensImmobiliers primary key (NumBiens),
                              constraint verif_typebiens check (TypeBiens in ('villa','appartement','logement')))
                              /
Create table Demande(NumDem number,
                     NumClt number not NULL,
                     NumBiens number not NULL,
                     typeDem varchar2(15),
                     Date_D Date,
                     constraint PK_Demande primary key (NumDem),
                     constraint FK_Demande_Client FOREIGN key (NumClt) references Client(NumClt) on delete cascade,
                     constraint FK_Demande_biensImmobiliers FOREIGN key (NumBiens) references biensImmobiliers(NumBiens) on delete cascade 
                     )
                     /
Create table AgentBiens(Numaffect number,
                        NumAgt number not NULL,
                        NumBiens number not NULL,
                        Date_A Date,
                        constraint PK_AgentBiens primary key (Numaffect),
                        constraint FK_AgentBiens_Agent FOREIGN key (NumAgt) references Agent(NumAgt) on delete cascade,
                        constraint FK_AgentBiens_biensImmobiliers FOREIGN key (NumBiens) references biensImmobiliers(NumBiens) on delete cascade)
                        /
Create table RDV(NumRdv number,
                 NumAgt number not NULL,
                 NumClt number not NULL,
                 NumBien number not NULL,
                 Date_rdv date not NULL,
                 constraint PK_RDV primary key (NumRdv),
                 constraint FK_RDV_Agent FOREIGN key (NumAgt) references Agent(NumAgt) on delete cascade,
                 constraint FK_RDV_Client FOREIGN key (NumClt) references Client(NumClt) on delete cascade)
                 /
Create table Transac(NumTran number,
                     TypeTran varchar2(20) not NULL,
                     NumClt number not NULL,
                     NumBiens number not NULL,
                     Date_T Date,
                     montant number,
                     constraint PK_Transaction primary key (NumClt,NumBiens,Date_T),
                     constraint FK_Transaction_Client FOREIGN key (NumClt) references Client(NumClt) on delete cascade,
                     constraint FK_Transaction_biens FOREIGN key (NumBiens) references biensImmobiliers(NumBiens) on delete cascade)
                         /
