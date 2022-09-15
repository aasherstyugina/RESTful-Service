CREATE TABLE IF NOT EXISTS items
(
    id    VARCHAR(50) NOT NULL ,
    url  VARCHAR(300) ,
    parentId VARCHAR(50) ,
    type VARCHAR(10)  NOT NULL ,
    size VARCHAR(50) ,
    date VARCHAR(50) NOT NULL
);