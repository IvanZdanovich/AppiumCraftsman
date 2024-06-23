# AppiumCraftsman

# Setup 
1. Provide relevant capabilities. Update example '.json' files in the 'resources' folder of the 'appium-test' project with relevant parameters. Unused capabilities could be wiped. Alternatively capabilities and parameters could be provided via CLI.
2. Run direct TestNG suite using following parameter: './gradlew runAppiumTests -Psuite=testng.xml' or set of parameters:
   ./gradlew runAppiumTests -PhubUrl=http://localhost:4723/wd/hub -Pplatform=android -PappPath=path/to/app.apk -PdeviceName=device1 -PplatformVersion=11.0 -Pudid=emulator-5554

