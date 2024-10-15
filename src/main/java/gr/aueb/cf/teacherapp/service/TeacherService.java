package gr.aueb.cf.teacherapp.service;

import gr.aueb.cf.teacherapp.mapper.Mapper;
import gr.aueb.cf.teacherapp.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.teacherapp.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.teacherapp.dto.TeacherInsertDTO;
import gr.aueb.cf.teacherapp.model.Teacher;
import gr.aueb.cf.teacherapp.model.static_data.Region;
import gr.aueb.cf.teacherapp.repository.RegionRepository;
import gr.aueb.cf.teacherapp.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final RegionRepository regionRepository;
    private final Mapper mapper;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Teacher saveTeacher(TeacherInsertDTO dto)
            throws EntityAlreadyExistsException, EntityInvalidArgumentException {

        if (teacherRepository.findByVat(dto.getVat()).isPresent()) {
            throw new EntityAlreadyExistsException("teacher", "teacher with vat " + dto.getVat() + " already exists");
        }
        Teacher teacher = mapper.mapToTeacherEntity(dto);
        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(()->new EntityInvalidArgumentException("region", "region not found"));

        teacher.setRegion(region);

        return teacherRepository.save(teacher);
    }
}
