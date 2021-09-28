# Logic Gates Simulator

## Description
Logic Gates Simulator it is a simple logic calculator with intuitive web user interface which was implemented by Java Spring Boot and Angular. Calculator works only with logic functions like:
- AND (^, *, &)
- OR (+, |)
- NOT (~, -, !)

After enter a formula to the field program automatically recognized input variables and write them down on the left bar of interface. By clicking on input variables You can change them on the opposite value:
- Green - TRUE (1)
- Red - FALSE (0)

<p align="center">
    <img src="https://github.com/savolodya/LogicGates/blob/main/readme_assets/inputs.png" alt="Inputs">
</p>

It can produce a normal result in the form of a variable **Y** (on the left bar) or it can also generate a **truth table** for a given task.

<p align="center">
    <img src="https://github.com/savolodya/LogicGates/blob/main/readme_assets/outputs.png" alt="Outputs">
</p>

##Project setup
Navigate to where you want your project to be contained.
```bash
git clone https://github.com/savolodya/LogicGates.git
cd angular-spring/ui
npm install             # Install javascript package requirements
ng build                # Build the Angular Frontend
ng serve                # Builds and serves your app, rebuilding on file changes.  
cd ../
mvnw spring-boot:run    # Build and run Spring Boot Backend
```

##Future scope
- Add drawing logic gates
