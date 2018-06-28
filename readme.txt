Procedure calls:

DROP PROCEDURE IF EXISTS FindMaxValue;

DELIMITER // 
CREATE PROCEDURE FindMaxValue(IN name TEXT, morn DATE, night DATE)
BEGIN 
SELECT MAX(price) FROM stock.stocks
WHERE symbol  = name && date > morn && date < night; 
END // 
DELIMITER ;

DROP PROCEDURE IF EXISTS FindMinValue;

DELIMITER // 
CREATE PROCEDURE FindMinValue(IN name TEXT, morn DATE, night DATE)
BEGIN 
SELECT MIN(price) FROM stock.stocks
WHERE symbol  = name && date > morn && date < night; 
END // 
DELIMITER ;


DROP PROCEDURE IF EXISTS SumVolume;

DELIMITER // 
CREATE PROCEDURE SumVolume(IN name TEXT, day1 DATE)
BEGIN 
SELECT SUM(volume) FROM stock.stocks
WHERE symbol  = name && DATEDIFF( date, day1) = 0; 
END // 
DELIMITER ;