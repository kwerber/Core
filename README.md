# Core
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/dashboard?id=ArDoCo_Core)

[![Maven Verify](https://github.com/ArDoCo/Core/workflows/Maven%20Verify/badge.svg)](https://github.com/ArDoCo/Core/actions?query=workflow%3A%22Maven+Verify%22)


The goal of this project is to connect architecture documentation and models while identifying missing or deviating elements. An element can be any representable item of the model, like a component or a relation.

This project is based on the master thesis [Linking Software Architecture Documentation and Models](https://doi.org/10.5445/IR/1000126194).

For more information about the setup or the architecture have a look on the [wiki](https://github.com/ArDoCo/Core/wiki/Overview).

## CLI
The Core Project contains a CLI that currently supports to find trace links between PCM Models and Textual SW Architecture Documentation. The CLI is part of the pipeline module of this project. The PCM models have to be converted to ontologies using [Ecore2OWL](https://github.com/kit-sdq/Ecore2OWL).

### Usage
```
usage: java -jar ardoco-core-pipeline.jar
 -c,--conf <arg>           path to the additional config file
 -h,--help                 show this message
 -i,--withimplementation   indicate that the model contains the code model
 -m,--model <arg>          path to the owl model
 -n,--name <arg>           name of the run
 -o,--out <arg>            path to the output directory
 -p,--provided             flag to show that ontology has text already
                           provided
 -t,--text <arg>           path to the text file
```

### Case Studies
To test the Core, you could use case studies provided in ..
* [ArDoCo Case Studies](https://github.com/ArDoCo/CaseStudies)
* [SWATTR](https://github.com/ArDoCo/SWATTR)
