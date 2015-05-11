Enter file contents hereCREATE DATABASE  IF NOT EXISTS `trivialpurfuit` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `trivialpurfuit`.`answers` (
  `reply_id` int(11) NOT NULL,
  `answer_key` longtext,
  `category_name` char(60) NOT NULL,
  `color_id` char(30) NOT NULL,
PRIMARY KEY `PRIMARY` (`reply_id`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`category` (
  `category_num` int(11) NOT NULL AUTO_INCREMENT,
  `change_count` int(11) NOT NULL,
  `category_name` char(60) NOT NULL,
  `color_id` char(30) NOT NULL,
  `session_id` int(11) NOT NULL,
  `round` int(11) NOT NULL,
PRIMARY KEY `PRIMARY` (`category_num`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`game_admin` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` char(60) NOT NULL,
  `admin_password` char(60) NOT NULL,
PRIMARY KEY `PRIMARY` (`session_id`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`game_session` (
  `player_count` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `round` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
PRIMARY KEY `PRIMARY` (`player_count`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`player` (
  `user_name` char(30) NOT NULL,
  `player_id` int(11) NOT NULL,
  `token_id` char(30) NOT NULL,
  `round` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
PRIMARY KEY `PRIMARY` (`user_name`),
UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`player_token` (
  `token_count` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` char(30) NOT NULL,
  `player_id` int(11) NOT NULL,
  `user_name` char(60) NOT NULL,
  `round` int(11) NOT NULL,
PRIMARY KEY `PRIMARY` (`token_count`)
) ENGINE=InnoDB;

CREATE TABLE `trivialpurfuit`.`questions` (
  `question_num` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` char(60) NOT NULL,
  `color_id` char(30) NOT NULL,
  `question_type` longtext,
  `answer_key` longtext,
  `reply_id` int(11) NOT NULL,
  `session_id` int(11) NOT NULL,
  `change_count` int(11) NOT NULL,
  `round` int(11) NOT NULL,
PRIMARY KEY `PRIMARY` (`question_num`)
) ENGINE=InnoDB;

USE `trivialpurfuit`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: trivialpurfuit
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,'Time to quit','try4','try5','try6','Places','Blue'),(2,'not sure','try2','try3','try4','People','Red'),(3,'Do not know','try7','try8','try9','Independence Day','Green'),(4,'The real','try11','try12','try13','Events','White');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,0,'Places','Blue',1,1),(2,0,'People','Red',1,1),(3,0,'Independence Day','Green',1,1),(4,0,'Events','White',1,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `game_admin`
--

LOCK TABLES `game_admin` WRITE;
/*!40000 ALTER TABLE `game_admin` DISABLE KEYS */;
INSERT INTO `game_admin` VALUES (1,'admin','Manager');
/*!40000 ALTER TABLE `game_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `game_session`
--

LOCK TABLES `game_session` WRITE;
/*!40000 ALTER TABLE `game_session` DISABLE KEYS */;
INSERT INTO `game_session` VALUES (1,1,2,2);
/*!40000 ALTER TABLE `game_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `player_token`
--

LOCK TABLES `player_token` WRITE;
/*!40000 ALTER TABLE `player_token` DISABLE KEYS */;
INSERT INTO `player_token` VALUES (1,'blue',1,'playerone',2);
/*!40000 ALTER TABLE `player_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What time is it?','Time to quit','try4','try5','try6','Places','Blue',1,1,0,1),(2,'What day is it?','not sure','try2','try3','try4','People','Red',2,1,0,1),(3,'Where is it?','Do not know','try7','try8','try9','Independence Day','Green',3,1,0,1),(4,'What plane is it?','The real','try11','try12','try13','Events','White',4,1,0,1);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'trivialpurfuit'
--
/*!50003 DROP PROCEDURE IF EXISTS `check_answer_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `check_answer_sp`(IN question_p longtext, IN answer_p longtext, OUT get_answer longtext)
    READS SQL DATA
BEGIN

set @question_ca = question_p;
set @answer_ca = answer_p;


SELECT answer_key INTO @get_answer_key
FROM answers 
WHERE reply_id = (SELECT reply_id FROM questions WHERE question_type = @question_ca and answer_key = @answer_ca);

if @get_answer_key != @answer_ca then

set get_answer ='Sorry, your answer is incorrect';

else

SET get_answer = @get_answer_key;

END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `game_admin_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `game_admin_sp`(IN p_user_name char(20), IN p_admin_password char(20))
    READS SQL DATA
proc: BEGIN

SET @user_name_ga = p_user_name;
SET @password_ga = p_admin_password;

IF p_admin_password != 'Manager' THEN
 Select 'Your password is incorrect' ;
 leave proc;
END IF;

INSERT INTO game_admin (user_name, admin_password)
VALUES (@user_name_ga, @password_ga);



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `game_session_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `game_session_sp`(IN player_id_gs INT)
    READS SQL DATA
BEGIN
set @player_id_gs = player_id_gs;

SELECT count(*) INTO @session_count
FROM game_admin;

IF @session_count is null then

set @round_gs = 0;
set @session_id_gs = 0;

else
SET @round_gs = @session_count;
SET @session_id_gs = @session_count;

END if;


INSERT INTO game_session (player_id, round, session_id)
VALUES (@player_id_gs, @round_gs, @session_id_gs);

 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_players_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `set_players_sp`(IN user_name_p char(20), IN token_id_p char(20))
    READS SQL DATA
BEGIN

SET @user_name_p = user_name_p;
SET @token_id_p = token_id_p;


SELECT count(*) INTO @player_count
FROM player;

IF @player_count is null then
SET @player_id_p = 0;

ELSE
SET @player_id_p = @player_count + 1;
END IF;

SELECT count(*) INTO @session_count
FROM game_admin;

IF @session_count is null then

SET @round_p = 0;
SET @session_id_p = 0;

ELSE
SET @round_p = @session_count;
SET @session_id_p = @session_count;

END IF;

INSERT INTO player (user_name, player_id, token_id, round, session_id)
VALUES (@user_name_p, @player_id_p, @token_id_p, @round_p, @session_id_p);

/*Update related tables*/

CALL game_session_sp (@player_id_p);
CALL set_player_token_sp (@token_id_p, @player_id_p, @user_name_p, @round_p);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_player_token_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `set_player_token_sp`(IN token_id_t char(10), IN player_id_t INT, IN user_name_t char(20), 
IN round_t INT)
    READS SQL DATA
BEGIN

SET @token_id_t = token_id_t;
SET @player_id_t = player_id_t;
SET @user_name_t = user_name_t;
SET @round_t = round_t;


INSERT INTO player_token (token_id, player_id, user_name, round)
VALUES (@token_id_t, @player_id_t, @user_name_t, @round_t);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_questions_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `set_questions_sp`(IN question_sq longtext, IN correct_answer_sq longtext,
IN answer2_sq longtext, IN answer3_sq longtext, IN answer4_sq longtext, IN category_name_sq char(50))
    READS SQL DATA
proc:BEGIN


SET @question_type_sq = question_sq;
SET @answer_key_sq = correct_answer_sq;
SET @answer2_sq = answer2_sq;
SET @answer3_sq = answer3_sq;
SET @answer4_sq = answer4_sq;
SET @category_name_sq = category_name_sq;




/*Check that the game administrator logged into the database for at least one session.*/

SELECT COUNT(*) into @number_of_sessions
FROM game_admin;

IF @number_of_sessions < 1 then

select 'The game administrator has not authenticated to the database.';
leave proc;
 
else

SET @session_id_sq = @number_of_sessions;
SET @change_count_sq = @number_of_sessions - 1;
SET @round_sq = @change_count_sq + 1;

END IF;

select COUNT(*) into @number_of_rows
from questions;

IF @number_of_rows is null then

SET @reply_id_sq = 0;

else

SET @reply_id_sq = @number_of_rows + 1;

END IF; 

IF @category_name_sq = 'People' then
SET @color_id_sq = 'Red'; 
elseif @category_name_sq = 'Events' then
SET @color_id_sq = 'White';
elseif @category_name_sq = 'Places' then
SET @color_id_sq = 'Blue';
elseif @category_name_sq = 'Independence Day' then
SET @color_id_sq = 'Green';

END IF;

INSERT INTO questions (question_type, answer_key, answer2, answer3, answer4, category_name, color_id, reply_id, change_count, session_id, round)
VALUES ( @question_type_sq, @answer_key_sq, @answer2_sq, @answer3_sq, @answer4_sq, @category_name_sq, @color_id_sq, @reply_id_sq, @change_count_sq, @session_id_sq, @round_sq); 

CALL track_category_sp(@change_count_sq, @category_name_sq, @color_id_sq, @session_id_sq, @round_sq);
CALL track_answers_sp(@reply_id_sq, @answer_key_sq, @answer2_sq, @answer3_sq, @answer4_sq, @category_name_sq, @color_id_sq);


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `track_answers_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `track_answers_sp`(IN p_reply_id INT, IN p_answer_key longtext, IN p_answer2 longtext, 
IN p_answer3 longtext, IN p_answer4 longtext, IN p_category_name char(50), IN p_color_id char(10))
    READS SQL DATA
BEGIN

SET @reply_id_a = p_reply_id;
SET @answer_key_a = p_answer_key;
SET @answer2 = p_answer2;
SET @answer3 = p_answer3;
SET @answer4 = p_answer4;
SET @category_name_a = p_category_name;
SET @color_id_a = p_color_id;


INSERT INTO answers (reply_id, answer_key, answer2, answer3, answer4, category_name, color_id)
VALUES (@reply_id_a, @answer_key_a, @answer2, @answer3, @answer4, @category_name_a, @color_id_a);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `track_category_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `track_category_sp`(IN p_change_count INT, IN p_category_name char(50), IN p_color_id char(10), IN p_session_id INT, IN p_round INT)
    READS SQL DATA
BEGIN


SET @change_count_c = p_change_count;
SET @category_name_c = p_category_name;
SET @color_id_c = p_color_id;
SET @session_id_c = p_session_id;
SET @round_c = p_round;



 INSERT INTO category (change_count, category_name, color_id, session_id, round) 
 VALUES (@change_count_c, @category_name_c, @color_id_c, @session_id_c, @round_c);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_questions_sp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`cbamba`@`%` PROCEDURE `update_questions_sp`(IN category_uq longtext, OUT question_uq_c longtext, OUT correct_answer_uq longtext, OUT answer2_uq longtext,
OUT answer3_uq longtext, OUT answer4_uq longtext, OUT category_name_uq char(50))
    READS SQL DATA
BEGIN

SET @category_uq = category_uq;



select question_type, answer_key, answer2, answer3, answer4, category_name
into @question_uq_c, @correct_answer_uq, @answer2_uq, @answer3_uq, @answer4_uq, @category_name_uq
from questions
where category_name = @category_uq;


SET question_uq_c = @question_uq_c;
SET correct_answer_uq = @correct_answer_uq ;
SET answer2_uq = @answer2_uq ;
SET answer3_uq = @answer3_uq ;
SET answer4_uq = @answer4_uq ;
SET category_name_uq = @category_name_uq ;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-11  0:12:13
