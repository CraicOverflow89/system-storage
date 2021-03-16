System Storage
==============

Lightweight tool that calculates the distribution of used storage of categories on a drive.

### Results

You can display results in the terminal that look something like this;

```
System Storage

[##############################################----]

Total Space                                222.54 GB

Operating System                            30.60 GB
Applications                                86.63 GB
User Files                                  55.24 GB
Other Files                                 31.89 GB

Free Space                                  18.18 GB
```

### Tasks

 - operating system recognition (categories are currently hardcoded for Windows)
 - consider what Operating System includes (eg: System32 and other things?)
 - consider available drives (command line arg? prompt and read line?)
 - improve categories (need to consider how to examine other files)
 - option to render in pie chart and other ways (frame with string source)
 - full test coverage