=============================================================================								
=								jturtle										=
========================by Ndombasi Diakusala Joao Andre=====================
=							February, 2024 China							=
=============================================================================

Introduction:
-----------
jturtle is a java implementation of the python 'turtle' module. I tried my best to make the codes syntax in python and java look very in terms of number of functions provided and functions names buy using abstraction. but the behind-the-scene implementation is far from being similar. At last, You're very expected to fork this repo and provide your contribution. Java is a powerful language, let's do this for newbies and children that get started with programming. 

(1) Find the jar file under the following path:
 =======> out/artifacts/jturtle_jar/jturtle.jar

(2) Import this jturtle.jar in your project. Add this dependency in your pom.xml IF using a maven dependencies manager.

<dependencies>
        <dependency>
            <groupId>com.njad</groupId>
            <artifactId>jturle</artifactId>
            <scope>system</scope>
            <version>1.0</version>
            <systemPath>${project.basedir}/jturtle.jar</systemPath>
		</dependency>
</dependencies>


(3) Functions implemented:
    public void showTurtle();
    public void hideTurtle();
    public void startAt(int x, int y);
    public void forward(int distance);
    public void backward(int distance);
    public void setDrawingSpeed(int speed);
    public void setPenSize(int size);
    public void right(double angle);
    public void left(double angle);
    public void circle(int radius);
