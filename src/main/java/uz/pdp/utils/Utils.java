package uz.pdp.utils;

import com.github.javafaker.*;
import com.github.javafaker.service.RandomService;
import uz.pdp.enums.FieldType;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import static uz.pdp.enums.FieldType.*;
import static uz.pdp.utils.RequestGenerator.logger;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);


    public static final Faker faker = new Faker();
    private static final AtomicLong id = new AtomicLong(1);
    private static final Country country = faker.country();
    private static final Address address = faker.address();
    private static final Internet internet = faker.internet();
    private static final Book book = faker.book();
    private static final Name name = faker.name();
    private static final Lorem lorem = faker.lorem();
    private static final RandomService random = faker.random();
    private static final PhoneNumber phoneNumber = faker.phoneNumber();
    private static final DateAndTime dateAndTime = faker.date();

    public static final Map<FieldType, Supplier<Object>> getFieldFromFieldTypes = new HashMap<>() {{
        put(ID, id::getAndIncrement);
        put(UUID, java.util.UUID::randomUUID);
        put(BOOK_TITLE, book::title);
        put(BOOK_AUTHOR, book::author);
        put(POST_TITLE, lorem::words);
        put(POST_BODY, lorem::paragraph);
        put(FIRSTNAME, name::firstName);
        put(LASTNAME, name::lastName);
        put(USERNAME, name::username);
        put(FULLNAME, name::fullName);
        put(BLOOD_GROUP, name::bloodGroup);
        put(EMAIL, () -> name.username() + "@" + (random.nextBoolean() ? "gmail.com" : "mail.ru"));
        put(RANDOM_INT, random::nextLong);
        put(GENDER, () -> random.nextBoolean() ? "male" : "female");
        put(PHONE, phoneNumber::phoneNumber);
        put(LOCAlDATE, () -> {
            int year = random.nextInt(1900, Year.now().getValue() - 1);
            int month = random.nextInt(1, 12);
            YearMonth yearMonth = YearMonth.of(year, month);
            int day = random.nextInt(1, yearMonth.getMonth().length(yearMonth.isLeapYear()));
            return LocalDate.of(year, month, day);
        });
        put(AGE, random::nextLong);
        put(COUNTRY_CODE, country::countryCode3);
        put(COUNTRY_ZIP_CODE, address::zipCode);
        put(CAPITAL, country::capital);
        put(WORD, lorem::word);
        put(WORDS, lorem::words);
        put(PARAGRAPH, lorem::paragraph);
        put(PARAGRAPHS, () -> lorem.paragraphs(10));
        put(LETTERS, lorem::characters);
    }};

    public static String readConsole(String hint) {
        System.out.print(hint);
        return scanner.nextLine();
    }

    public static void showFileType() {
        System.out.println();
        System.out.println("1 -> JSON");
        System.out.println("2 -> CSV");
        System.out.println("3 -> SQL");
        System.out.println("s -> STOP APPLICATION");
    }

    public static void showFieldType() {
        System.out.println("1   -> ID               14  -> GENDER");
        System.out.println("2   -> UUID             15  -> PHONE");
        System.out.println("3   -> BOOK_TITLE       16  -> LOCAlDATE");
        System.out.println("4   -> BOOK_AUTHOR      17  -> AGE");
        System.out.println("5   -> POST_TITLE       18  -> COUNTRY_CODE");
        System.out.println("6   -> POST_BODY        19  -> COUNTRY_ZIP_CODE");
        System.out.println("7   -> FIRSTNAME        20  -> CAPITAL");
        System.out.println("8   -> LASTNAME         21  -> WORD");
        System.out.println("9   -> USERNAME         22  -> WORDS");
        System.out.println("10  -> FULLNAME         23  -> PARAGRAPH");
        System.out.println("11  -> BLOOD_GROUP      24  -> PARAGRAPHS");
        System.out.println("12  -> EMAIL            25  -> LETTERS");
        System.out.println("13  -> RANDOM_INT");
    }

    public static FieldType getFieldType(String choiceFieldType) {
        return switch (choiceFieldType) {
            case "1" -> ID;
            case "2" -> UUID;
            case "3" -> BOOK_TITLE;
            case "4" -> BOOK_AUTHOR;
            case "5" -> POST_TITLE;
            case "6" -> POST_BODY;
            case "7" -> FIRSTNAME;
            case "8" -> LASTNAME;
            case "9" -> USERNAME;
            case "10" -> FULLNAME;
            case "11" -> BLOOD_GROUP;
            case "12" -> EMAIL;
            case "13" -> RANDOM_INT;
            case "14" -> GENDER;
            case "15" -> PHONE;
            case "16" -> LOCAlDATE;
            case "17" -> AGE;
            case "18" -> COUNTRY_CODE;
            case "19" -> COUNTRY_ZIP_CODE;
            case "20" -> CAPITAL;
            case "21" -> WORD;
            case "22" -> WORDS;
            case "23" -> PARAGRAPH;
            case "24" -> PARAGRAPHS;
            case "25" -> LETTERS;
            default -> {
                logger.warning("Invalid field type: ");
                throw new IllegalStateException("Unexpected value: " + choiceFieldType);
            }
        };
    }

}
