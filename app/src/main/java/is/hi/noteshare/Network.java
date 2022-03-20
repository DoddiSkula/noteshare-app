package is.hi.noteshare;


public class Network {

    getCourses() {
        // return list of dummy courses
        return List<
        new Course(1 ,HBV401G, Þróun hugbúnaðar, HÍ),
        new Course(2, HBV501G, Hugbúnaðarverkefni 1, HÍ),
        new Course(3, HBV601G, Hugbúnaðarverkefni 2, HÍ),
        new Course(4, TÖL101G, Tölvunarfræði 1, HÍ),
        new Course(5, TÖL107G, Vefforritun 1, HÍ),
        new Course(6, HBV201G, Viðmótsforritun, HÍ),
        new Course(7, TÖL104G, Stærðfræðimynstur, HÍ),
        new Course(8, TÖL203G, Tölvunarfræði 2, HÍ),
        new Course(9, TÖL401G, Stýrikerfi, HÍ),
        new Course(10, TÖL304G, Forritunarmál, HÍ)
        >
    };

    getUsers() {
        return List< new User(1, admin, admin@admin.com, 123)
        >
    };
    
    /*
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
    unFavourite(userId: long, courseId: long);*/
}
