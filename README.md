# Scanny - Only for Andorid

 A document scanner plugin for Android.

## Getting Started

This plugin is built for android for now. Scan the document and you would get the image of the document

## Add the dependency
```
    dependencies:
        scanny: ^0.0.1
```

## Install the plugin
```
flutter pub get

```


## Usage
Instantiate Scanny plugin and pass it to any custom widget

### For instance
```
Instantiate the Scanny plugin 
class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState(new Scanny());
}
```

## Scanny provides you with 2 methods

1. askPermissions  - for asking camera and storage permissions
2. callScanner  - This will instiate the scanner activity of the android
    

### askPermissions is straight forward to use

#### For instance
```
      Future<void> askPermissions() async {
        await scanny.askPermissions;
      }

```  

### callScanner usage 
Use this method to initiate the scanning activity 
```
 dynamic _imageBytes;
 Future<void> scanDocument() async {
    try {
      //ask permissions if permissions are not granted yet
      await askPermissions();
      //call the scanner
       scanny.callScanner;
       //listen to the results of activity
       scanny.getImageBytes.listen((imageBytes){
         setState(() {
           _imageBytes = imageBytes;
         });
       });
    }
    catch(error) {
      print(error);
    }
  }
```

### Results - 
We get the image results in the form of imageBytes so we store them in dynamic datatype for the sake of simplicity
```
 scanny.getImageBytes.listen((imageBytes){
         setState(() {
           _imageBytes = imageBytes;
         });
       });
```

### Displaying the results in Flutter
Displaying of the returned images can be done using ```Image.memory``` widget.
#### For instance
```
Image.memory(_imageBytes
```

## For contributions 
Do Create pull and contribute. 

## TODO
1. Add IOS scanner (Major)
2. Add passport scanning functionality
 
# Thanks 
I would really like to thank  

1. https://github.com/Credntia/CVScanner for this wonderful scanner 
2. https://github.com/Diastorm/rn-doc-scanner-android 
 
# License 

GNU General Public License v3.0
Permissions of this strong copyleft license are conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed work, under the same license. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights.
 