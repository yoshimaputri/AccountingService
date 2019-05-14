package com.service.accounting.service;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.exception.NotFoundException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.repository.PendapatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PendapatanServiceImpl implements PendapatanService {
    private PendapatanRepository repository;

    @Autowired
    public void setRepository(PendapatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pendapatan newPendapatan(Pendapatan pendapatan) {
        return repository.save(pendapatan);
    }

    public Pendapatan getPendapatanById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("peng_id", id));
    }

    @Override
    public Pendapatan updatePendapatan(Pendapatan pendapatan) {
        Pendapatan check = getPendapatanById(pendapatan.getIdPendapatan());
        if (check != null) {
            if (pendapatan.getTanggal() != null) {
                check.setTanggal(pendapatan.getTanggal());
            }
            if (pendapatan.getIdRestaurant() != null) {
                check.setIdRestaurant(pendapatan.getIdRestaurant());
            }
            if (pendapatan.getKeterangan() != null) {
                check.setKeterangan(pendapatan.getKeterangan());
            }
            if (pendapatan.getJumlah() != null) {
                check.setJumlah(pendapatan.getJumlah());
            }
            return repository.save(check);
        } else {
            throw new NotFoundException("pend_id", pendapatan.getIdPendapatan());
        }
    }

    @Override
    public List<Pendapatan> getPendapatan(String idRestaurant, Integer start, Integer limit) {
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
                    List<Pendapatan> result = repository.findAllByIdRestaurant(idRestaurant);
                    if ((result.size() - start) < limit) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + limit);
                    }
                } else {
                    // start, 300
                    List<Pendapatan> result = repository.findAllByIdRestaurant(idRestaurant);
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
                    List<Pendapatan> result = repository.findAllByIdRestaurant(idRestaurant);
                    if (result.size() < limit) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, limit);
                    }
                } else {
                    // 0, 300
                    List<Pendapatan> result = repository.findAllByIdRestaurant(idRestaurant);
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
                if (start > repository.count()) {
                    throw new InputFormatException("Start value greater than the number of data we have.");
                }
                if (limit != null) {
                    if (limit < 1) {
                        throw new InputFormatException("Limit value must greater than 0.");
                    }
                    List<Pendapatan> result = repository.findAll();
                    if ((result.size() - start) < limit) {
                        return result.subList(start, result.size());
                    } else {
                        return result.subList(start, start + limit);
                    }
                } else {
                    List<Pendapatan> result = repository.findAll();
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
                    List<Pendapatan> result = repository.findAll();
                    if (result.size() < limit) {
                        return result.subList(0, result.size());
                    } else {
                        return result.subList(0, limit);
                    }
                } else {
                    List<Pendapatan> result = repository.findAll();
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
    public Long getNumberOfPendapatan() {
        return repository.count();
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun) {
        Stream<Pendapatan> stream = repository.findAll().stream();
        return stream.filter(pendapatan -> pendapatan.getTanggal().split("-")[0].equals(tahun))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan) {
        Stream<Pendapatan> stream = repository.findAll().stream();
        return stream.filter(pendapatan -> {
            String[] dateToken = pendapatan.getTanggal().split("-");
            return dateToken[0].equals(tahun) && dateToken[1].equals(bulan);
        }).collect(Collectors.toList());
    }
}
