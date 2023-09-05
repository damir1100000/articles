import dao.*;
import util.Keyboard;

import java.sql.SQLException;

class Menu {
    private ArticleDao articleDao;
    private UserDao userDao;

    public Menu(ArticleDao articleDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.userDao = userDao;
    }
    public void start () throws SQLException {
        int option = 0;

        while(option !=3) {
            System.out.println("1. Sign in");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            option = Keyboard.nextInt();

            if(option == 1) {
                signIn();
            } else if (option == 2) {
                register();
            } else if (option == 3) {
                System.out.println("Bye");
            }
        }
    }

    public void signIn () throws SQLException {
        System.out.println("Please enter the email");
        String email = Keyboard.nextString();

        System.out.println("Please enter the password");
        String password = Keyboard.nextString();

        User user = userDao.getUserByEmailAndPassword(email, password);

        if(user != null) {
            articlesOptions(user.getUserid());
            System.out.println("Autentificare realizata cu succes");
        } else {
            System.out.println("dao.User/parola gresita");
        }
    }
    public void register () throws SQLException {
        System.out.println("Please enter the email");
        String email = Keyboard.nextString();

        System.out.println("Please enter the password");
        String password = Keyboard.nextString();

        User user = userDao.createUser(email, password);

        if(user != null) {
            System.out.println("Registration successfully done");
        } else {
            System.out.println("Registration failed");
        }
    }
    public void articlesOptions (long userid) throws SQLException {
        int opt = 0;

        while(opt != 5) {
            System.out.println("1. View all articles");
            System.out.println("2. View your articles");
            System.out.println("3. Add article");
            System.out.println("4. Like article");

            opt = Keyboard.nextInt();

            if(opt == 1 ) {
                for(Article a : articleDao.allArticles()) {
                    System.out.println(a.getTitle() + " by " + userDao.getUserById(a.getUseriId()).getEmail() + " - " + a.getLikes() + " likes");
                }
            }

            if(opt == 2) {
                for(Article a : articleDao.getArticlesByUserid(userid)) {
                    System.out.println(a.getTitle() + " - " + a.getLikes() + " likes");
                }
            }

            if(opt == 3) {
                System.out.println("Please enter the title");
                String title = Keyboard.nextString();

                if(articleDao.addArticle(userid, title)) {
                    System.out.println("Article successfully added");
                } else {
                    System.out.println("Failed to add an article");
                }
            }

            if(opt == 4) {
                System.out.println("Please enter the title of the article that you want to like");
                String title = Keyboard.nextString();

                if(articleDao.likeArticle(title)) {
                    System.out.println("You liked the "+title+" article");
                } else {
                    System.out.println("Failed to like the article");
                }
            }
        }
    }

}