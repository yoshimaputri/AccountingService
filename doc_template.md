# Documentation Template

> Here is a documentation sample template. Put a simple description about your API here.

## Table of Content
- [Documentation Template](#documentation-template)
  - [Table of Content](#table-of-content)
  - [Base URL](#base-url)
  - [API Resources](#api-resources)
    - [Sample Object](#sample-object)
      - [Structure](#structure)
      - [Example](#example)
  - [Endpoints](#endpoints)
    - [Create Sample](#create-sample)
      - [Parameters](#parameters)
      - [Responses](#responses)
    - [Get Sample](#get-sample)
      - [Responses](#responses-1)
    - [Edit Sample](#edit-sample)
      - [Parameters](#parameters-1)
      - [Responses](#responses-2)
    - [Delete Sample](#delete-sample)
      - [Responses](#responses-3)

## Base URL

```
https://www.example.com/api/v1
```

## API Resources

> Explain every object that we will use on this API

### Sample Object

> You can add some description about this object

#### Structure

| Field            | Type            | Description          |
| ---------------- | --------------- | -------------------- |
| sample_id        | integer         | A usual ID           |
| some_field       | string          | A dummy field        |
| some_other_field | integer         | More dummy field     |
| fields           | array of string | A lot of dummy field |

#### Example
```json
{
    "sample_id": 123,
    "some_field": "some value",
    "some_other_field": 169,
    "fields": ["here", "is", "more", "fields"]
}
```

## Endpoints

> Explain every endpoints that available on this API

### Create Sample

#### `POST` /sample  <!-- omit in toc -->

> Give simple explanation about this endpoint

#### Parameters

| Field            | Type            | Optional? | Description          |
| ---------------- | --------------- | --------- | -------------------- |
| some_field       | string          | no        | A dummy field        |
| some_other_field | integer         | no        | More dummy field     |
| fields           | array of string | yes       | A lot of dummy field |

#### Responses

| Code | Description                                                              |
| ---- | ------------------------------------------------------------------------ |
| 201  | Sample Object successfully created. Returns newly created Sample Object. |
| 400  | Invalid input detected.                                                  |
| 401  | Unauthorized request.                                                    |

### Get Sample

#### `GET` /sample/{sample_id}  <!-- omit in toc -->

> Give simple explanation about this endpoint

#### Responses

| Code | Description                                                                         |
| ---- | ----------------------------------------------------------------------------------- |
| 200  | Sample Object successfully created. Returns Sample Object with matched `sample_id`. |
| 401  | Unauthorized request.                                                               |
| 404  | This resource not found.                                                            |

### Edit Sample

#### `PUT` /sample/{sample_id}  <!-- omit in toc -->
#### `PATCH` /sample/{sample_id}  <!-- omit in toc -->

> Give simple explanation about this endpoint

#### Parameters

| Field            | Type            | Optional? | Description          |
| ---------------- | --------------- | --------- | -------------------- |
| some_field       | string          | yes       | A dummy field        |
| some_other_field | integer         | yes       | More dummy field     |
| fields           | array of string | yes       | A lot of dummy field |

#### Responses

| Code | Description                                                       |
| ---- | ----------------------------------------------------------------- |
| 201  | Sample Object successfully created. Returns edited Sample Object. |
| 400  | Invalid input detected.                                           |
| 401  | Unauthorized request.                                             |

### Delete Sample

#### `DELETE` /sample/{sample_id}  <!-- omit in toc -->

> Give simple explanation about this endpoint

#### Responses

| Code | Description                                          |
| ---- | ---------------------------------------------------- |
| 204  | Sample Object with `sample_id` successfully deleted. |
| 401  | Unauthorized request.                                |
| 404  | This resource not found.                             |