BELFramework Web Service Plugin
==============================

This Cytoscape Plugin provides access to the webservice client for the BELFramework.

License
-------
This project is licensed under the terms of the [LGPL v3](http://www.gnu.org/licenses/lgpl.txt).

Building
--------

To build this plugin you will need

-   [ANT](http://ant.apache.org/)
-   BELFramework 1.2.2 or greater installed
-   Cytoscape 2.8.x installed

Once installed you will need to configure the *HOME* location of each

-   Uncomment and set the `BELFRAMEWORK_HOME` property in build.properties to
    point to the BELFramework installation folder.
-   Uncomment and set the `CYTOSCAPE_HOME` property in build.properties to point
    to the Cytoscape installation folder.

To build the project use the following commands

-   `ant package`

    Builds the plugin ready to install into Cytoscape.

-   `ant deploy`

    Packages the plugin and copies it to the plugins folder of your Cytoscape installation.


Setting up Eclipse
------------------

To set up the [Eclipse IDE](http://www.eclipse.org/) for working with this plugin

-   Check out the [belframework project](https://belframework-org@github.com/belframework-org/belframework.git)

    `git clone https://belframework-org@github.com/belframework-org/belframework.git`

-   In Eclipse go to *File -> New -> Java Project* and uncheck *Use default location*.

-   Name your project belframework-webservice-plugin.

-   Enter the location of your local belframework-webservice-plugin folder.

    `/path/to/git/clone/belframework/belframework-webservice-plugin`

-   Hit Ok and a new project will be created.

-   Configure classpath

    -   Add `BELFrameworkWebAPIClient-1.2.2.jar` (or later version) from BELFRAMEWORK_HOME/lib/webapiclient to the project classpath.
    -   Add `cytoscape.jar` from CYTOSCAPE_HOME/ to the project classpath.

-   (optional) To build with ANT drag the build.xml to the ANT view.
