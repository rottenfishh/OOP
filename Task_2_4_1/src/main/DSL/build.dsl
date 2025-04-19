build tool "gradlew.bat" {
    build "compileJava"
    test  "test"
    checkstyle "checkstyleMain checkstyleTest" 
    docgeneration "javadoc"
}
