# AccountingService

Sistem Informasi Accounting Restoran. 

## Anggota

1. 05111640000022 - Yoshima Syach Putri
2. 05111640000062 - Muhammad Auliaramadani
3. 05111640000069 - Deddy Aditya Pramana

## Milestone Checklist

- [x] Datamodel
- [x] Implementation
- [x] Documentation
- [x] Testing

## Revision Checklist
- [ ] Datamodel
- [ ] Implementation
- [ ] Documentation
- [ ] Testing

## Documentation

Swagger documentation: 
https://app.swaggerhub.com/apis-docs/yoshimaputri/swagger-accounting/1.0.0

## Available Endpoints

These endpoints consumes JSON and produces JSON. Form-data version has been deprecated and we will focus on JSON-version only.

```
POST /pendapatan
POST /pengeluaran

PUT /pendapatan/{id}
PUT /pengeluaran/{id}

PATCH /pendapatan/{id}
PATCH /pengeluaran/{id}

GET /pendapatan
GET /pendapatan/{tahun}
GET /pendapatan/{tahun}/{bulan}
GET /pengeluaran
GET /pengeluaran/{tahun}
GET /pengeluaran/{tahun}/{bulan}
```

