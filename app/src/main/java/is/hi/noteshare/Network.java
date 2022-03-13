package is.hi.noteshare;



public class Network {
    getCourses();
    getCourses(name: String);
    getCourse(id: long);
    getUser(id: long);
    getFilesByCourse(id: long);
    getFile(id: long);
    login(user: User);
    logout(user: User);
    signup(user: User);
    upload(file: File);
    favourite(userId: long, courseId: long);
    unFavourite(userId: long, courseId: long);
}
