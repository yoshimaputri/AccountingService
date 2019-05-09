# AccountingService

Branch ini berisi versi API yang menerima JSON-data. Gunakan branch master jika ingin versi yang menggunakan Multipart Form/URL-encoded form.

## Anggota

1. 05111640000022 - Yoshima Syach Putri
2. 05111640000062 - Muhammad Auliaramadani
3. 05111640000069 - Deddy Aditya Pramana

## Milestone Checklist

- [x] Datamodel
- [x] Implementation
- [ ] Documentation
- [x] Testing

## Documentation

For detail in swagger documentation: <link>

### POST
```
/pendapatan
/pengeluaran
```
### PUT|PATCH
```
/pendapatan/{id}
/pengeluaran/{id}
```
### GET
```
/pendapatan
/pendapatan/{tahun}
/pendapatan/{tahun}/{bulan}
/pengeluaran
/pengeluaran/{tahun}
/pengeluaran/{tahun}/{bulan}
```