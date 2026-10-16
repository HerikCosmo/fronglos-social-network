package cosmo.herik.fronglos.mapper;

import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "author", source = "user")
    PostResponse toPostResponse(Post post);
}
