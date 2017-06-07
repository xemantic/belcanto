*simple web service (api) which allows audiologists (hearing careprofessionals) to manage their customers appointments
– a mini CRM.*

# project structure

It is multi-module maven project, business model
is split from service layer logic.

## belcanto-model

JPA based domain model, keeping it as a separate module
will prevent from possible indirect dependency on the
service layer

