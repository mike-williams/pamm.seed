Directives
=

PAMM is primarily an enterprise applications architecture and is not aimed at generic UI component development such as a date picker.  Although there is nothing in PAMM that will prevent developers from doing so, they are encouraged to develop these outside of PAMM and import them via as normal Anuglar modules.

**PAMM uses Angular directives in two ways:**


Decorators
-
These are simple attribute directive that add functionality to the underlying data element.  For example, uppercase which will convert the model value automatically.

Features and Business Components
-
These are standalone components that perform a business function.  For example, an "account details" component that has a single purpose for display the details of a bank account.  Component are agnostic of each other and to the embedding feature.

See [Feature, Business Process, Components](features-and-components.md)
