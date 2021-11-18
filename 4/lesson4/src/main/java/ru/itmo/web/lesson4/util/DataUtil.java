package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", User.DataColor.GREEN),
            new User(6, "pashka", "Pavel Mavrin", User.DataColor.BLUE),
            new User(9, "geranazarov555", "Georgiy Nazarov", User.DataColor.GREEN),
            new User(11, "tourist", "Gennady Korotkevich", User.DataColor.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, 9, "Codeforces Round #748 (Div. 3)", "Привет, Codeforces!\n" + "Рад пригласить Вас на увлекательный (а мы постарались его сделать таким) Codeforces Round #748 (Div. 3) — раунд для третьего дивизиона, который состоится в среда, 13 октября 2021 г. в 17:35. Это раунд, сделанный мной (MrPaul_TUser), существенный вклад в его создание которого также внёс MikeMirzayanov.\n"  +
                    "Этот раунд содержит 6-8 задач. Задачи подобраны по сложности так, чтобы составить интересное соревнование для участников с рейтингами до 1600. Однако все желающие, чей рейтинг 1600 и выше, могут зарегистрироваться на раунд вне конкурса.\n"  +
                    "Раунд пройдет по правилам образовательных раундов. Таким образом, во время раунда задачи будут тестироваться на предварительных тестах, а после раунда будет 12-часовая фаза открытых взломов. Мы постарались сделать сильные тесты — так же как и Вы будем весьма удивлены, если у многих попадают решения после окончания контеста.\n"  +
                    "Вам будет предложено 6-8 задач и 2 часа 15 минут на их решение.\n"  +
                    "Штраф за неверную попытку в этом раунде (и последующих Div. 3 раундах) будет равняться 10 минутам.\n"  +
                    "Напоминаем, что в таблицу официальных результатов попадут только достоверные участники третьего дивизиона. Как написано по ссылке — это вынужденная мера для борьбы с неспортивным поведением. Для квалификации в качестве достоверного участника третьего дивизиона надо:\n"  +
                    "принять участие не менее чем в двух рейтинговых раундах (и решить в каждом из них хотя бы одну задачу),\n" +
                    "не иметь в рейтинге точку 1900 или выше. Независимо от того, являетесь вы достоверными участниками третьего дивизиона или нет, если ваш рейтинг менее 1600, то раунд для вас будет рейтинговым.\n" +
                    "Всем удачи и хорошего настроения!"),
            new Post(2, 11, "Educational Codeforces Round 115 [рейтинговый для Div. 2]", "Привет, Codeforces!\n" +
                    "В воскресенье, 10 октября 2021 г. в 12:05 состоится Educational Codeforces Round 115 (рейтинговый для Div. 2). Обратите внимание на необычное время старта раунда.\n"  +
                    "Продолжается серия образовательных раундов в рамках инициативы Harbour.Space University! Подробности о сотрудничестве Harbour.Space University и Codeforces можно прочитать в посте.\n"  +
                    "Этот раунд будет рейтинговым для участников с рейтингом менее 2100. Соревнование будет проводиться по немного расширенным правилам ICPC. Штраф за каждую неверную посылку до посылки, являющейся полным решением, равен 10 минутам. После окончания раунда будет период времени длительностью в 12 часов, в течение которого вы можете попробовать взломать абсолютно любое решение (в том числе свое). Причем исходный код будет предоставлен не только для чтения, но и для копирования.\n"  +
                    "Вам будет предложено 6 или 7 задач на 2 часа. Мы надеемся, что вам они покажутся интересными.\n" +
                    "Задачи вместе со мной придумывали и готовили Александр fcspartakm Фролов, Михаил awoo Пикляев и Максим Neon Мещеряков. Также большое спасибо Михаилу MikeMirzayanov Мирзаянову за системы Polygon и Codeforces.\n"  +
                    "Удачи в раунде! Успешных решений!\n" +
                    "Также от наших друзей и партнёров из Harbour.Space есть сообщение для вас:\n"  +
                    "Привет, Codeforces!\n" +
                    "Мы поздравляем одного из наших преподавателей Николая KAN Калинина с его первым местом в финале чемпионата мира ICPC, который проходил в Москве, Россия. Годы тренировок Николая и его команды из Нижегородского государственного университета привели их к вершине турнирной таблицы, победе над командами из 116 других университетов и чемпионству.\n"  +
                    "Также поздравляем нашего будущего студента Егора kefaa2 Дубовика, завоевавшего серебряную медаль в составе команды Белорусского государственного университета. Егор присоединится к нам в магистратуре \"Computer Science\" в ближайшие недели.\n"  +
                    "Мы с нетерпением ждем встречи с Николаем снова в январе следующего года, когда он будет вести свой курс Advanced Algorithms and Data Structures вместе с Майком Мирзаяновым. В этом курсе студенты сосредотачиваются на ключевых алгоритмах и структурах данных, которые составляют инструментарий современного специалиста.\n"  +
                    "Мы всегда рады видеть участников сообщества Codeforces в качестве наших студентов здесь, в Harbour.Space, поэтому мы снова предоставили специальную скидку (до 70%) на участие в одном курсе в Барселоне, Испания (расходы на проезд и проживание не включены).\n"  +
                    "Забронировать место →\n" + "Codeforces and Harbour.Space\n"  + "Желаем удачи и до встречи в следующий раз!\n"  + "Harbour.Space University\n" ),
            new Post(3, 1, "Технокубок 2022 — олимпиада по программированию для школьников", "Привет!\n"  +
                    "Наступил октябрь, а значит, пришло время объявить, что Mail.ru Group совместно с МФТИ, МГТУ им. Н.Э.Баумана и CodeForces запускает седьмой по счету «Технокубок» — олимпиаду за звание самого талантливого молодого программиста среди учеников 8-11 классов!\n"  +
                    "«Технокубок» входит в проект Перечня олимпиад школьников РСОШ, дающих льготы при поступлении в высшие учебные заведения РФ, как олимпиада I уровня — победители и призеры финального этапа олимпиады смогут поступить в вуз без экзаменов или получить 100 баллов за ЕГЭ по информатике. Кроме того, все победители «Технокубка» получат ценные призы, а также привилегии при поступлении на образовательные проекты Mail.ru Group.\n"  +
                    "Финал олимпиады пройдет в марте 2022 года. Формат его проведения будет известен позднее в связи с эпидемиологической обстановкой.\n"  + "Этапы соревнования\n" +
                    "Ознакомительные раунды\n" + "Перед каждым отборочным раундом будет проходить двухдневный ознакомительный раунд, в котором можно потренироваться и проверить свои силы. Решение ознакомительных задач не влияет на результат. Кроме того, в это время участники смогут ознакомиться с платформой Codeforces, чтобы избежать возможных проблем во время основных этапов.\n"  +
                    "15 октября 2021 г. в 13:05 — 17 октября 13:05\n" + "12 ноября 2021 г. в 08:05 — 14 ноября 08:05\n" + "10 декабря 2021 г. в 17:05 — 12 декабря 17:05\n" + "Время указано в формате МСК (GMT +3).\n"  +
                    "Отборочные (онлайн) этапы\n" + "Отборочные этапы напрямую влияют на выход в финал – лучшие участники каждого раунда будут приглашены на заключительный этап (но не более 45% от общего числа участников раунда). Пользователи могут участвовать в отборочных раундах до тех пор, пока не попадут в списки финалистов. Приглашенные на заключительный этап должны подтвердить желание и возможность участия в нем. В случае отсутствия подтверждения жюри может пригласить следующего участника по результатам соответствующего онлайн-раунда.\n" +
                    "\n" + "Первый отборочный раунд: 17 октября 2021 г. в 14:05\n" + "Второй отборочный раунд: 14 ноября 2021 г. в 09:05\n" + "Третий отборочный раунд: 12 декабря 2021 г. в 18:05\n" + "Время указано в формате МСК (GMT +3). Обратите внимание, что на решение задач отборочного этапа дается два часа.\n"  +
                    "Количество отборочных раундов может быть увеличено.\n"  + "Финальный (очный) этап\n" + "Финальный (заключительный) раунд пройдет в марте 2022 г. В связи с эпидемиологической обстановкой, о формате проведения финала будет сообщено позднее на портале чемпионата: он может пройти очно или заочно с применением дистанционных технологий, в том числе прокторинга.\n"  +
                    "Победители и призеры олимпиады познакомятся со специалистами крупнейшей IT-компании в России, а также узнают про возможности карьерного роста в IT-сфере для абитуриентов МФТИ, МГТУ им. Н. Э. Баумана и других высших учебных заведений страны.\n"  + "Льготы:\n" +
                    "Олимпиада школьников «Технокубок» включена в проект Перечня олимпиад школьников РСОШ, дающих льготы при поступлении в высшие учебные заведения РФ как олимпиада I уровня.\n"  +
                    "Всего будет награждено не более трети участников заключительного этапа.\n"  + "Участник, занявший первое место, получает также главный приз Олимпиады — сам Технокубок."),
            new Post(6, 11, "Mirror of Bubble Cup 14 Finals on Codeforces","Hello, Codeforces!\n" +
                    "Microsoft Development Center Serbia is thrilled to announce the finals of the 14th edition of Bubble Cup competition! Bubble Cup is an international, ICPC-style team contest aimed at university and high school students.\n"  + " \n"  +
                    "Contest will take place on Saturday, 9th of October at 10AM CEST, in online format. Winners will be announced at the closing ceremony. You can find more info on the BubbleCup website.\n"  +
                    "Just like the previous editions, this final will be followed by an online mirror competition on Codeforces. Mirror will take place on the same day about an hour after the start of the finals — суббота, 9 октября 2021 г. в 12:05. Contest will last for 4 hours and ICPC rules will be applied. It will be a competition for teams of 1-3 members. There will be at least eight problems.\n"  +
                    "Just like last year, the finals are divided in two \"divisions\", called Premier League and Rising Stars. The two contests will have most of their problems in common, but the Rising Stars competition will feature some easier tasks targeted at high school contestants.\n"  +
                    "Both of the contests will be mirrored here on Codeforces, with Premier League mapping to the Div1 contest and Rising Stars mapping to the Div2 contest. The mirror will use native Codeforces ICPC team contest rules. Each team is allowed to use multiple computers.\n"  +
                    "Both contests will be unrated, due to the format and the length of the mirror being dissimilar to the standard Codeforces rated rounds.\n"  +
                    "The problems and their solutions were created by employees and interns of Microsoft Development Center Serbia: niksmiljkovic, acac97, renea, BubbleCup, nikolapesic2802, berke00, davidmilicevic97, ijevtic, dj0l3, igzi, Kole, Vasiljko, pavlej and me TadijaSebez.\n"  +
                    "We give our thanks to Nikolay Kalinin (KAN) and Mike Mirzayanov (MikeMirzayanov) for making these mirror contests possible and for the wonderful Codeforces and Polygon platforms. Special thanks goes to Alexandr Lyashko (knightL) for helping out with problem testing.\n"  +
                    "You can find problems from previous finals on our Codeforces online mirror competitions:")
    );
    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
        data.put("posts", POSTS);
        data.put("requestLink", request.getRequestURI());

    }
}
