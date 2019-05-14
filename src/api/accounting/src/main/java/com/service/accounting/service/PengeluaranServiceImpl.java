package com.service.accounting.service;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.exception.NotFoundException;
import com.service.accounting.model.Pengeluaran;
import com.service.accounting.repository.PengeluaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementasi dari interface PengeluaranService
 */
@Service
public class PengeluaranServiceImpl implements PengeluaranService {
    private PengeluaranRepository repository;

    @Autowired
    public void setRepository(PengeluaranRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pengeluaran newPengeluaran(Pengeluaran pengeluaran) {
        return repository.save(pengeluaran);
    }

    @Override
    public Pengeluaran getPengeluaranById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("peng_id", id));
    }

    @Override
    public Pengeluaran updatePengeluaran(Pengeluaran pengeluaran) {
        Pengeluaran check = getPengeluaranById(pengeluaran.getIdPengeluaran());
        if (check != null) {
            if (pengeluaran.getTanggal() != null) {
                check.setTanggal(pengeluaran.getTanggal());
            }
            if (pengeluaran.getIdRestaurant() != null) {
                check.setIdRestaurant(pengeluaran.getIdRestaurant());
            }
            if (pengeluaran.getKeterangan() != null) {
                check.setKeterangan(pengeluaran.getKeterangan());
            }
            if (pengeluaran.getJumlah() != null) {
                check.setJumlah(pengeluaran.getJumlah());
            }
            return repository.save(check);
        } else {
            throw new NotFoundException("peng_id", pengeluaran.getIdPengeluaran());
        }
    }

    @Override
    public List<Pengeluaran> getPengeluaran(String idRestaurant, Integer start, Integer limit) {
        if (idRestaurant != null) {
            if (start != null) {
                if (start < 0) {
                    throw new InputFormatException("Start value must greater than or equal 0.");
                }
                if (start > repository.count()) {
                    throw new InputFormatException("Start value greater than the number of data we have.");
                }
                if (limit != null) {
                    if (limit < 1) {
                        throw new InputFormatException("Limit value must greater than 0.");
                    }
                    // start, limit
                    List<Pengeluaran> result = repository.findAllByIdRestaurant(idRestaurant);
                    if ((result.size() - start) < limit) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + limit);
                    }
                } else {
                    // start, 300
                    List<Pengeluaran> result = repository.findAllByIdRestaurant(idRestaurant);
                    if ((result.size() - start) < 300) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + 300);
                    }
                }
            } else {
                if (limit != null) {
                    if (limit < 1) {
                        throw new InputFormatException("Limit value must greater than 0.");
                    }
                    // 0, limit
                    List<Pengeluaran> result = repository.findAllByIdRestaurant(idRestaurant);
                    if (result.size() < limit) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, limit);
                    }
                } else {
                    // 0, 300
                    List<Pengeluaran> result = repository.findAllByIdRestaurant(idRestaurant);
                    if (result.size() < 300) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, 300);
                    }
                }
            }
        }
        else {
            if (start != null) {
                start--;
                if (start < 0) {
                    throw new InputFormatException("Start value must greater than 0.");
                }
                if (start > repository.count() - 1) {
                    throw new InputFormatException("Start value greater than the number of data we have.");
                }
                if (limit != null) {
                    if (limit < 1) {
                        throw new InputFormatException("Limit value must greater than 0.");
                    }
                    List<Pengeluaran> result = repository.findAll();
                    if ((result.size() - start) < limit) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + limit);
                    }
                } else {
                    List<Pengeluaran> result = repository.findAll();
                    if ((result.size() - start) < 300) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + 300);
                    }
                }
            } else {
                if (limit != null) {
                    if (limit < 1) {
                        throw new InputFormatException("Limit value must greater than 0.");
                    }
                    List<Pengeluaran> result = repository.findAll();
                    if (result.size() < limit) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, limit);
                    }
                } else {
                    List<Pengeluaran> result = repository.findAll();
                    if (result.size() < 300) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, 300);
                    }
                }
            }
        }
    }

    @Override
    public Long getNumberOfPengeluaran() {
        return repository.count();
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun) {
        Stream<Pengeluaran> stream = repository.findAll().stream();
        return stream.filter(pendapatan -> pendapatan.getTanggal().split("-")[0].equals(tahun))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun, String bulan) {
        Stream<Pengeluaran> stream = repository.findAll().stream();
        return stream.filter(pendapatan -> {
            String[] dateToken = pendapatan.getTanggal().split("-");
            return dateToken[0].equals(tahun) && dateToken[1].equals(bulan);
        }).collect(Collectors.toList());
    }
}
