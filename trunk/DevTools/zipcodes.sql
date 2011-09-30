use scc//

drop table if exists scc.craigs_cities//

drop table if exists scc.zip_codes//
create table scc.zip_codes (zip varchar(6) not null primary key, latitude long not null, longitude long not null, city varchar(1000) not null, state varchar(10) not null)//

load data local infile 'ZIP_CODES.txt' into table scc.zip_codes fields terminated by ',' lines terminated by '\n' (zip, latitude, longitude, city, state)//

DROP PROCEDURE IF EXISTS `GetNearbyZipCodes`//
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetNearbyZipCodes`(  
    zipbase  varchar (6),  
    ranges  numeric (15)  
)
BEGIN  
DECLARE  lat1  decimal (5,2);  
DECLARE  long1  decimal (5,2);  
DECLARE  rangeFactor  decimal (7,6);  
SET  rangeFactor = 0.014457;  
SELECT  latitude,longitude  into  lat1,long1  FROM zip_codes  WHERE  zip = zipbase;  
SELECT  B.zip  
FROM  zip_codes  AS  B   
WHERE    
 B.latitude  BETWEEN  lat1-(ranges*rangeFactor)  AND  lat1+(ranges*rangeFactor)  
  AND  B.longitude  BETWEEN  long1-(ranges*rangeFactor)  AND  long1+(ranges*rangeFactor)  
  AND  GetDistance(lat1,long1,B.latitude,B.longitude)  <= ranges;  
END //

DROP PROCEDURE IF EXISTS `GetNearbyCities`//
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetNearbyCities`(  
    zipbase  varchar (6),  
    ranges  numeric (15)  
)
BEGIN  
DECLARE  lat1  decimal (5,2);  
DECLARE  long1  decimal (5,2);  
DECLARE  rangeFactor  decimal (7,6);  
SET  rangeFactor = 0.014457;  
SELECT  latitude,longitude  into  lat1,long1  FROM zip_codes  WHERE  zip = zipbase;  
SELECT DISTINCT UCASE(B.city), UCASE(B.state) 
FROM  zip_codes  AS  B   
WHERE    
 B.latitude  BETWEEN  lat1-(ranges*rangeFactor)  AND  lat1+(ranges*rangeFactor)  
  AND  B.longitude  BETWEEN  long1-(ranges*rangeFactor)  AND  long1+(ranges*rangeFactor)  
  AND  GetDistance(lat1,long1,B.latitude,B.longitude)  <= ranges
  ORDER BY B.city;  
END //

DROP FUNCTION IF EXISTS `GetDistance`//
CREATE DEFINER=`root`@`localhost` FUNCTION `GetDistance`(  
 lat1  numeric (9,6),  
 lon1  numeric (9,6),  
 lat2  numeric (9,6),  
 lon2  numeric (9,6)  
) RETURNS decimal(10,5)
BEGIN  
  DECLARE  x  decimal (20,10);  
  DECLARE  pi  decimal (21,20);  
  SET  pi = 3.14159265358979323846;  
  SET  x = sin( lat1 * pi/180 ) * sin( lat2 * pi/180  ) + cos(  
 lat1 *pi/180 ) * cos( lat2 * pi/180 ) * cos(  abs( (lon2 * pi/180) -  
 (lon1 *pi/180) ) );  
  SET  x = atan( ( sqrt( 1- power( x, 2 ) ) ) / x );  
  RETURN  ( 1.852 * 60.0 * ((x/pi)*180) ) / 1.609344;  
END //