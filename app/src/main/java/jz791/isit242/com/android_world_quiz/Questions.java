package jz791.isit242.com.android_world_quiz;

// type of quiz
enum QuestionType {
    geography,
    history,
    literature
}

class Question {
    String question;
    String correctAns;
    String wrongAns1;
    String wrongAns2;
    String wrongAns3;

    public Question(String question, String correctAns, String wrongAns1, String wrongAns2, String wrongAns3) {
        this.question = question;
        this.correctAns = correctAns;
        this.wrongAns1 = wrongAns1;
        this.wrongAns2 = wrongAns2;
        this.wrongAns3 = wrongAns3;
    }
}

public class Questions {

    static Question[] geography = {
            new Question("What is the capital in Turkey?", "Ankara", "Oslo", "Singapore", "Munich"),
            new Question("How many states are there in Australia?", "6", "5", "4", "7"),
            new Question("How many world parts are there?", "7", "6", "8", "4"),
            new Question("Which of these countries are not in Europe?", "Lebanon", "Luxembourg", "Ukraine", "Greece"),
            new Question("Which is the world's highest mountain?", "Mount Everest", "K2", "Kilimanjaro", "Makalu"),
            new Question("Which is the longest river in the US?", "Missouri River", "Yukon River", "Colorado River", "Mississippi River"),
            new Question("The biggest desert in the world is...?", "Sahara", "Great Australian", "Arabian", "Somewhere in Africa"),
            new Question("Which of these cities are not in Europe?", "Perth", "Moscow", "Oslo", "Prague"),
            new Question("The united Kingdom is comprised of how many countries?", "4", "6", "8", "5"),
            new Question("Which US state is the Grand Canyon located in?", "Arizona", "New Mexico", "Nevada", "Wyoming"),
            new Question("Which is the largest body of water", "Pacific Ocean", "Indian Ocean", "Atlantic Ocean", "The pool in my backyard"),
            new Question("What is the biggest country in the world, measured by total land area?", "Russia", "China", "Africa", "Australia"),
            new Question("What country is also known as Persia?", "Iran", "Irak", "Islam", "India"),
            new Question("In what country would you find Mount Kilimanjaro?", "Tanzania", "Africa", "South-Africa", "Nigeria"),
            new Question("What major river flows through the Grand Canyon?", "Colorado River", "Mississippi River", "Missouri River", "Yukon River"),
            new Question("In what city is the Brandenburg Gate located?", "Berlin", "Munich", "Drammen", "Dortmund"),
            new Question("What city was once called New Amsterdam?", "New York City", "Amsterdam in 1834", "Las Vegas", "Manchester"),
            new Question("What country is often described as being shaped like a boot?", "Italy", "Norway", "Australia", "Sweden"),
            new Question(" In which country is the Hillary-Tenzing Airport situated?", "Nepal", "Tanzania", "South Africa", "Uganda"),
            new Question("By surface area, what is the largest of Africa's great lakes?", "Victoria", "George", "Missisippi", "Eden")
    };

    static Question[] history = {
            new Question("Which year did World War 1 begin in?", "1914", "1918", "1938", "1923"),
            new Question("Which country was Adolf Hitler born in", "Austria", "Germany", "France", "Hungary"),
            new Question("John F. Kennedy was assasinated in", "Dallas", "Miami", "New York", "Austin"),
            new Question("Who fought in the war of ", "1914", "1918", "1938", "1923"),
            new Question("Who was the \"Man Of Steel\"", "Stalin", "Steeling", "Lenin", "No one"),
            new Question("The disease that ravaged and killed a third of Europe's population in the 14th century is known as:", "The Black Plague", "Smallpox", "The White Death", "The Bubonic Plague"),
            new Question("The Hundred Years War was fought between what two countries?", "France and England", "Spain and France", "Italy and Carthage", "Rome and Gallia"),
            new Question("Who wrote the 95 Theses?", "Martin Luther", "Saint Augustus", "Jesus Christ", "Voltaire"),
            new Question("Which country did Germany invade on the 1st of September 1939?", "Poland", "France", "Finland", "Czechoslovakia"),
            new Question("What was the first country to give women the vote in 1893?", "New Zealand", "Australia", "America", "Iceland"),
            new Question("Which of these was one of the seven ancient wonders of the world?", "Lighthouse of Alexandria", "Great Wall of China", "Taj Mahal", "Macchu Picchu"),
            new Question("What year did the Berlin Wall fall?", "1989", "1999", "1991", "1988"),
            new Question("When was Rome founded?", "around 750BC", "around 100BC", "1st century", "2nd century"),
            new Question("Which year did World War 2 begin in?", "1939", "1941", "1938", "1940"),
            new Question("The Great Pyramid of Giza was built for which Egyptian ruler?", "Cheops", "Kleopatra", "Khufu", "Ramses II"),
            new Question("Name the person who built Fort Necessity", "George Washington", "Abraham Lincoln", "Benjamin Franklin", "Barack Obama"),
            new Question("Who was first to reach the South Pole?", "Captain Amundsen", "John Steilener", "James Madison", "Captain Khmer"),
            new Question("Who was Julius Caesar's successor after his death?", "Augustus", "Remus", "Romulus", "Nero"),
            new Question("Who was the first president of the United States?", "George Washington", "Thomas Jefferson", "Abraham Lincoln", "Benjamin Franklin"),
            new Question("When did Australia gain their independence from Britain?", "1901", "1920", "1888", "1905")
    };

    static Question[] literature = {
            new Question("Which two cities provide the setting for Charles Dickens’s ‘A Tale of Two Cities'?", "London and Paris", "Oslo and Munich", "London and Birmingham", "Birmingham and Munich"),
            new Question("Tony Stark is the alter ego of which Marvel Comics super hero?", "Iron Man", "The Hulk", "Superman", "Spiderman"),
            new Question("Which is the last book of the Old Testament?", "Malachi", "Lucas", "Paulius", "Paul"),
            new Question("In which century was William Shakespeare born?", "16th century", "15th century", "17th century", "18th century"),
            new Question("What is Frankenstein’s first name in Mary Shelley’s novel?", "Victor", "Vladimir", "Frank", "Frankstie"),
            new Question("How many books does the \"Harry Potter\" series have?", "7", "6", "8", "5"),
            new Question("Which famous work of literature is this opening line from: \"All children, except one, grow up.\"", "Peter Pan", "Romeo and Juliet", "The Stranger", "Alice in Wonderland"),
            new Question("How many lines are there in a sonnet?", "Fourteen", "Twelve", "Nine", "Fifteen"),
            new Question("‘Bring Up the Bodies’ is a 2012 historical novel by which English writer?", "Hilary Mantel", "Joshua Jenkins", "J. K. Rowling", "J. R. R. Tolkien"),
            new Question("Who wrote 'The Hobbit'?", "J. R. R. Tolkien", "J. K. Rowling", "J. J. J. Tolkien", "J. R. Tolkien"),
            new Question("Who hunted Moby-Dick?", "Captain Ahab", "Captain Hook", "Cap'n Crush", "Captain America"),
            new Question("Who wrote the book '1984'?", "George Orwell", "George Owell", "There is no such book", "Vladimir Wolshevok"),
            new Question("What nationality was the playwright Henrik Ibsen?", "Norwegian", "Australian", "German", "Russian"),
            new Question("Who wrote the plays 'The Wasps', 'The Birds', and 'The Frogs'?", "Aristophanes", "Aristocrates", "Socrates", "Aristoteles"),
            new Question("Which novel was set in a bureaucratic totalitarian state, 35 years ahead of the book's publication date?", "Nineteen Eighty-four", "Harry Potter", "The Good, the Bad and the Evil", "No novel"),
            new Question("Who wrote an elegy on Abraham Lincoln called When Lilacs Last in the Dooryard Bloom'd'?", "Walt Whitman", "Walter White", "Hugh Jackman", "Jo Nesbo"),
            new Question("Which English poet planned a Communist colony in America with Robert Southey?", "Samuel Taylor Coleridge", "Abraham Lincoln", "Samuel L. Jackson", "Marl Henriksen"),
            new Question("Who wrote Romeo and Juliet?", "William Shakespeare", "Willam Shakespeare", "Will Shakespeare", "Woodwill Shakespeare"),
            new Question("Who wrote this set of awesome questions?", "Me", "You", "He", "She"),
            new Question("Who is the narrator of Arthur Conan Doyle's Sherlock Holmes stories?", "Dr. Watson", "Sherlock Holmes", "Marlock Kendrick", "Arthur Conan")
    };
}
