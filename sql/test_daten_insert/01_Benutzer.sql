/*==================================================================*/
/* Name:		   01_Benutzer                                      */
/* Created on:     05.03.2024                                       */
/* Creater:        Jade HS/Winf                                     */
/* Company:        Jade HS                                          */
/* Aufgabe:        Testdaten Benutzer                               */ 
/*==================================================================*/

--USE DBXXXXXXX

--Administratoren
INSERT INTO ED_BENUTZER VALUES ('BA001', 'Mueller', 'Kevin',   'M', '10.05.1993', 'mueller@fsn.de', '04421-23556', null, 'A', null, null);

INSERT INTO ED_BENUTZER VALUES ('BA002', 'Hein',    'Daniel',  'M', '21.09.1984', 'hein@fsn.de',    '04421-23557', null, 'A', null, null);

INSERT INTO ED_BENUTZER VALUES ('BA003', 'Volk',    'Natalie', 'W', '02.04.1998', 'volk@fsn.de',    '04421-23558', null, 'A', null, null);

INSERT INTO ED_BENUTZER VALUES ('BA004', 'Schmidt', 'Kevin',   'M', '13.10.1979', 'schmidt@fsn.de', '04421-23559', null, 'A', null, null);

INSERT INTO ED_BENUTZER VALUES ('BA005', 'Frank',   'Silke',   'W', '27.01.1989', 'frank@fsn.de',   '04421-23510', null, 'A', null, null);


--Bussiness Konten (Unternehmen, Influencer, etc.)
INSERT INTO ED_BENUTZER VALUES ('BB006', 'Jade Hochschule',      null,     null, null,         'oeffentliches@jade-hs.de', null,            'Friedrich-Paffrath-Str. 101 26389 Wilhelmshaven', 'B', 17, 1);

INSERT INTO ED_BENUTZER VALUES ('BB007', 'Asta Whv',             null,     null, null,         'asta@jade-hs.de',          null,            'Friedrich-Paffrath-Str. 101 26389 Wilhelmshaven', 'B', 14, 1);

INSERT INTO ED_BENUTZER VALUES ('BB008', 'Pumpwerk Whv',         null,     null, null,         'pumpwerk@whv.de',          '04421-789',     'Banter Deich 26389 Wilhelmshaven',                'B', 14, 1);

INSERT INTO ED_BENUTZER VALUES ('BB009', 'DFB-Team',             null,     null, null,         'dfb@team.de',              null,            null,                                              'B', 24, null);

INSERT INTO ED_BENUTZER VALUES ('BB010', 'Tagesschau',           null,     null, null,         'tagesschau@erstes.de',     null,            'Hugh-Greene-Weg 1 22529 Hamburg',                 'B', 33, 2);

INSERT INTO ED_BENUTZER VALUES ('BB011', 'Stadttheater Whv',     null,     null, null,         'theater@whv.de',           '04421-940115',  'Virchowstr. 44 26382 Wilhelmshaven',              'B', 15, 1);

INSERT INTO ED_BENUTZER VALUES ('BB012', 'Nautimo Whv',          null,     null, null,         'nautimo@whv.de',           '04421-773550',  'Friedensstr. 99 26386 Wilhelmshaven',             'B',  9, null);

INSERT INTO ED_BENUTZER VALUES ('BB013', 'ZDF Info',             null,     null, null,         'info@zdf.de',              null,            'Mainz',                                           'B', 27, 2);

INSERT INTO ED_BENUTZER VALUES ('BB014', 'Bar Celona Whv',       null,     null, null,         'celona@whv.de',            '04421-5066440', 'Bahnhofsstrasse 1 26382 Wilhelmshaven',           'B', 16, 2);

INSERT INTO ED_BENUTZER VALUES ('BB015', 'Swift',                'Taylor', 'w',  '13.12.1989', 'taylor@swiftie.com',       null,            null,                                              'B', 35, null);

INSERT INTO ED_BENUTZER VALUES ('BB016', 'Klum',                 'Heidi',  'w',  '01.07.1973', 'klum@klum.com',            null,            'Los Angeles',                                     'B', 36, null);

INSERT INTO ED_BENUTZER VALUES ('BB017', 'Atlantic Hotel',       null,     null, null,         'atlantic@whv.de',          '04421-773380',  'Jadeallee 50 26382 Wilhelmshaven',                'B', 11, null);

INSERT INTO ED_BENUTZER VALUES ('BB018', 'Sportsfreund Fitness', null,     null, null,         'fitness@whv.de',           '04421-1814272', 'Gross Belt 19 26389 Wilhelmshaven',               'B', 21, null);

INSERT INTO ED_BENUTZER VALUES ('BB019', 'Kling Klang Whv',      null,     null, null,         'klingklang@whv.de',        '04421-13322',   'Boersenstrasse 72 26382 Wilhelmshaven',           'B', 16, 4);

INSERT INTO ED_BENUTZER VALUES ('BB020', 'kleinstadtcoco',       null,     'w',  '28.01.1995', 'kleinstadtcoco@whv.de',    null,            'Wilhelmshaven',                                   'B', 23, 9);


--Oeffentliche Konten
INSERT INTO ED_BENUTZER VALUES ('BO021', 'Neuner',     'Niko',    'M', '07.01.1998', 'neuner@gmail.com',     null, 'Wilhelmshaven', 'O',  6, 10);

INSERT INTO ED_BENUTZER VALUES ('BO022', 'Fundig',     'Lydia',   'W', '15.02.1997', 'fundig@web.de',        null, 'Wilhelmshaven', 'O',  8, 9);

INSERT INTO ED_BENUTZER VALUES ('BO023', 'Liebherr',   'Carla',   'W', '22.10.1999', 'liebherr@gmail.com',   null, 'Rastede',       'O',  9, 7);

INSERT INTO ED_BENUTZER VALUES ('BO024', 'Meyer',      'Achim',   'M', '28.09.2000', 'meyer@gmail.com',      null, 'Jever',         'O',  4, 15);

INSERT INTO ED_BENUTZER VALUES ('BO025', 'Schmidt',    'Karl',    'M', '04.09.2003', 'schmidt@gmx.de',       null, 'Jever',         'O',  6, 9);

INSERT INTO ED_BENUTZER VALUES ('BO026', 'Ritter',     'Bernd',   'M', '21.08.1965', 'ritter@gmail.com',     null, 'Wilhelmshaven', 'O',  4, 17);

INSERT INTO ED_BENUTZER VALUES ('BO027', 'Engel',      'Nicole',  'W', '10.09.1978', 'engel@outlook.de',     null, 'Wilhelmshaven', 'O',  4, 15);

INSERT INTO ED_BENUTZER VALUES ('BO028', 'Bostelmann', 'Claudia', 'W', '09.11.1977', 'bostelmann@gmail.com', null, 'Sande',         'O',  6, 10);

INSERT INTO ED_BENUTZER VALUES ('BO029', 'Kaiser',     'Sandra',  'W', '27.12.1998', 'kaiser@gmail.com',     null, 'Wilhelmshaven', 'O',  4, 12);

INSERT INTO ED_BENUTZER VALUES ('BO030', 'Knoll',      'Helge',   'M', '16.12.1991', 'knoll@web.de',         null, 'Wilhelmshaven', 'O',  4, 17);

INSERT INTO ED_BENUTZER VALUES ('BO031', 'Blum',       'Marie',   'W', '12.04.1990', 'blum@gmx.de',          null, null,            'O',  6, 8);

INSERT INTO ED_BENUTZER VALUES ('BO032', 'Wolf',       'Charlie', 'M', '11.06.2002', 'wolf@outlook.de',      null, 'Wilhelmshaven', 'O',  7, 12);

INSERT INTO ED_BENUTZER VALUES ('BO033', 'Hecker',     'Malte',   'M', '04.05.1990', 'hecker@gmail.com',     null, null,            'O',  3, 10);

INSERT INTO ED_BENUTZER VALUES ('BO034', 'Bassler',    'Helga',   'W', '21.05.1960', 'bassler@gmail.com',    null, 'Wilhelmshaven', 'O',  4, 10);

INSERT INTO ED_BENUTZER VALUES ('BO035', 'Schneider',  'Sonja',   'W', '11.02.1972', 'schneider@gmail.com',  null, 'Sande',         'O',  5, 8);

INSERT INTO ED_BENUTZER VALUES ('BO036', 'Schroeder',  'Wiebke',  'W', '06.05.2004', 'schroeder@outlook.de', null, 'Wilhelmshaven', 'O', 10, 11);

INSERT INTO ED_BENUTZER VALUES ('BO037', 'Kiel',       'Ludo',    'M', '08.07.2000', 'kiel@gmail.com',       null, 'Wilhelmshaven', 'O',  7, 10);

INSERT INTO ED_BENUTZER VALUES ('BO038', 'Tucker',     'Milla',   'W', '15.09.1997', 'tucker@gmx.de',        null, 'Wilhelmshaven', 'O', 11, 9);

INSERT INTO ED_BENUTZER VALUES ('BO039', 'Eggers',     'Esther',  'W', '18.12.1999', 'eggers@web.de',        null, null,            'O',  5, 9);

INSERT INTO ED_BENUTZER VALUES ('BO040', 'Kapinzki',   'Steffen', 'M', '27.01.1985', 'kapinzki@web.de',      null, 'Wilhelmshaven', 'O',  4, 10);

INSERT INTO ED_BENUTZER VALUES ('BO041', 'Rose',       'Mia',     'W', '12.12.1993', 'rose@outlook.de',      null, 'Wilhelmshaven', 'O',  4, 11);

INSERT INTO ED_BENUTZER VALUES ('BO042', 'Druss',      'Rudi',    'M', '06.07.2002', 'druss@gmail.com',      null, 'Wilhelmshaven', 'O',  7, 16);

INSERT INTO ED_BENUTZER VALUES ('BO043', 'Boll',       'Nico',    'M', '12.08.1974', 'boll@gmail.com',       null, 'Jever',         'O',  8, 12);

INSERT INTO ED_BENUTZER VALUES ('BO044', 'Merkel',     'Katrin',  'W', '17.09.2001', 'merkel@gmail.com',     null, 'Wilhelmshaven', 'O',  3, 10);

INSERT INTO ED_BENUTZER VALUES ('BO045', 'Scholz',     'Olaf',    'M', '08.06.1986', 'scholz@outlook.de',    null, 'Wilhelmshaven', 'O',  6, 13);

INSERT INTO ED_BENUTZER VALUES ('BO046', 'Frei',       'Lea',     'W', '29.06.1988', 'frei@web.de',          null, 'Wilhelmshaven', 'O',  5, 9);

INSERT INTO ED_BENUTZER VALUES ('BO047', 'Boettcher',  'Vivian',  'W', '20.06.2000', 'boettcher@gmx.de',     null, 'Schortens',     'O',  7, 11);

INSERT INTO ED_BENUTZER VALUES ('BO048', 'Muehlmann',  'Hugo',    'M', '02.06.2002', 'muehlmann@web.de',     null, 'Wilhelmshaven', 'O',  7, 9);

INSERT INTO ED_BENUTZER VALUES ('BO049', 'Walstra',    'Robin',   'M', '03.03.1981', 'walstra@gmail.com',    null, null,            'O',  5, 8);


--Private Konten
INSERT INTO ED_BENUTZER VALUES ('BP050', 'Leutner',    'Rosi',    'W', '20.02.1980', 'leutner@web.de',      null, null,            'P',  9, 9);

INSERT INTO ED_BENUTZER VALUES ('BP051', 'Zacharias',  'Lena',    'W', '30.07.1992', 'zacharias@gmail.com', null, null,            'P',  7, 12);

INSERT INTO ED_BENUTZER VALUES ('BP052', 'Becker',     'Hanna',   'W', '04.03.1995', 'becker@gmx.de',       null, null,            'P',  4, 9);

INSERT INTO ED_BENUTZER VALUES ('BP053', 'Seiler',     'Kevin',   'M', '16.03.1976', 'seiler@gmail.com',    null, null,            'P',  2, 9);

INSERT INTO ED_BENUTZER VALUES ('BP054', 'Leutloff',   'Alex',    'M', '12.09.1979', 'leutloff@outlook.de', null, 'Wilhelmshaven', 'P',  4, 8);

INSERT INTO ED_BENUTZER VALUES ('BP055', 'Muench',     'Lisa',    'W', '03.06.1970', 'muench@web.de',       null, null,            'P',  6, 11);

INSERT INTO ED_BENUTZER VALUES ('BP056', 'Jaeger',     'Simon',   'M', '23.10.1989', 'jaeger@web.de',       null, null,            'P',  4, 9);

INSERT INTO ED_BENUTZER VALUES ('BP057', 'Otto',       'Frank',   'M', '25.10.1998', 'otto@gmail.com',      null, 'Wilhelmshaven', 'P',  4, 14);

INSERT INTO ED_BENUTZER VALUES ('BP058', 'Volk',       'Nadja',   'W', '21.09.2003', 'volk@web.de',         null, 'Wilhelmshaven', 'P',  5, 15);

INSERT INTO ED_BENUTZER VALUES ('BP059', 'Grade',      'Miranda', 'W', '03.07.1998', 'grade@gmx.de',        null, null,            'P',  3, 14);

INSERT INTO ED_BENUTZER VALUES ('BP060', 'Taube',      'Betty',   'W', '09.05.2001', 'taube@gmail.com',     null, 'Wilhelmshaven', 'P',  5, 16);

INSERT INTO ED_BENUTZER VALUES ('BP061', 'Klum',       'Heike',   'W', '10.03.2000', 'klum@outlook.de',     null, null,            'P',  7, 16);

INSERT INTO ED_BENUTZER VALUES ('BP062', 'Adel',       'Boris',   'M', '02.03.1986', 'adel@web.de',         null, 'Wilhelmshaven', 'P',  4, 15);

INSERT INTO ED_BENUTZER VALUES ('BP063', 'Reichert',   'Tamara',  'W', '01.02.1974', 'reichert@gmail.com',  null, 'Schortens',     'P',  5, 14);

INSERT INTO ED_BENUTZER VALUES ('BP064', 'Singer',     'Rahel',   'W', '22.01.1986', 'singer@gmail.com',    null, null,            'P',  4, 18);

INSERT INTO ED_BENUTZER VALUES ('BP065', 'Sanders',    'Marina',  'W', '27.10.1992', 'sanders@gmail.com',   null, null,            'P',  6, 12);

INSERT INTO ED_BENUTZER VALUES ('BP066', 'Bayer',      'Marta',   'W', '07.12.2004', 'bayer@gmx.de',        null, 'Jever',         'P',  8, 11);

INSERT INTO ED_BENUTZER VALUES ('BP067', 'Utus',       'Erkan',   'M', '10.04.2002', 'utus@outlook.de',     null, null,            'P', 10, 12);

INSERT INTO ED_BENUTZER VALUES ('BP068', 'Schmidt',    'Caro',    'W', '10.05.1988', 'schmidt@gmail.com',   null, null,            'P',  4, 10);

INSERT INTO ED_BENUTZER VALUES ('BP069', 'Huber',      'Petra',   'W', '01.04.1990', 'huber@web.de',        null, 'Sande',         'P',  6, 11);

INSERT INTO ED_BENUTZER VALUES ('BP070', 'Dauer',      'Pascal',  'M', '04.04.2000', 'dauer@web.de',        null, null,            'P',  9, 12);


SELECT * FROM ED_BENUTZER