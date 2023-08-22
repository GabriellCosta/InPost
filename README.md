# InPost Recruitment Task

To read more about the propose of the task, please refer to [OBJECTIVE_README.md](OBJECTIVE_README.md)

## Setup

This project was made using the latest android plugin at the moment, because of that there some
recommendations before running

- Use JDK 17

## Run test

### Unit tests

To run unit tests use:

```
make test_unit
```

### End to End tests

To run end to end tests use:

```
make test_end_to_end
```

These tests use [maestro](https://maestro.mobile.dev/), you will need to install it, you can do 
it running

```
make install_maestro
```

## Decisions

The core tasks of the app where to display a list of shipments, separating then in two groups, I 
decided to threat all items as a single list and organize them, I have a type that I can use to 
control the type that will be displayed, `ShipmentItemType`, if a new type of header or item is needed
we can simple create a new Ui implementation that the compiler will ask you to do it and then decide
how the list will handle it (`GroupResponseUseCase`)

### For Ui

For ui [Jetpack Compose](https://developer.android.com/jetpack/compose) is used

Would be nice to add some improvements in the theming part as I hardcoded the colors and the typography
could be wrong, I better approach would be define a color pallet and a typography scheme, so we
could have better consistency in our app

The Archive option is done by a swipe, an animation could be added, this way the user would have 
a better feedback

### For Data

The sample provide us a typedImplementation for ZonedDateTime, this api is not normally supported by our
an android app, so I decided to use the [desugaring option](https://developer.android.com/studio/write/java8-support-table)
this way I can easily support ZoneDateTime without problems

To retrieve the local and API data I tried to do a SingleSourceOfTruth approach using Flow and room,
so i would always return the data from the same place

### For Dependencies

Here I'm using gradle catalog to handle and control my dependencies, as Android Studio now better support
it is very easy to use and control, and is a good fit to create a routine to automatic update the project
dependencies if needed (using renovate for example)

### Modularization

In some moment I had the idea to make this project modular, as I think that there's no small project for modules
I decide to abort this mission due to time to do the initial configuration as I want to finish this project.
Because of that we have only one extra module (`infra`), But a ideal configuration here for me would be

- app -> Central module
- feature/shipment;etc
- infra/network;etc
- theming/core;etc

Also we should focus in deliver pure kotlin modules instead of android module, we could use 
the public/impl/wire approach to handle this better, this way we can have a lot of benefits in change
of the initial configuration, of course the team need to be align with some modularization patterns

### CI/CD

A simple run test job is added