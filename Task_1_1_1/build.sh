javadoc -d documentation/ -sourcepath src/main/java -subpackages oop.kolodina

javac -d class src/main/java/oop/kolodina/HeapSort.java

jar cfe HeapSort.jar oop.kolodina.HeapSort -C class .

java -jar HeapSort.jar