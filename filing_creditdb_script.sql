INSERT INTO `creditdb`.`gender` (`id`, `gender`, `gender_le`) VALUES (1,'Мужской',1),(2,'Женский',0);

INSERT INTO `creditdb`.`education` (`id`, `type`) VALUES (1,'Среднее общее'),(2,'Среднее профессиональное'),(3,'Высшее');

INSERT INTO `creditdb`.`odds` (`id`, `age`, `gender`, `marital`, `dependents`, `income`, `experience`, `real_estate`, `monthly_payment`, `intercept`) VALUES (1,-0.13,-0.3,-0.15,-1.66,0.0004,0.04,0.2,-0.0009,-0.28);

INSERT INTO `creditdb`.`position` (`id`, `type`) VALUES (1,'Руководитель'),(2,'Специалист'),(3,'Другая');

INSERT INTO `creditdb`.`rate` (`id`, `rate`) VALUES (1,12.00);

INSERT INTO `creditdb`.`terms` (`id`, `term`, `months_term`) VALUES (1,'1 месяц',1),(2,'3 месяца',3),(3,'6 месяцев',6),(4,'9 месяцев',9),(5,'1 год',12),(6,'2 года',24),(7,'3 года',36),(8,'4 года',48),(9,'5 лет',60);

