package com.schiot.community.service;

import com.schiot.community.entity.Member;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.form.WriteForm;
import com.schiot.community.repository.MemberPostRepository;
import com.schiot.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteService {

    private final MemberRepository memberRepository;
    private final MemberPostRepository memberPostRepository;

    public Member getWriteMember(String studentId, String password){
        List<Member> member = memberRepository.findByStudentIdAndMemberPassword(studentId, password);
        return member.get(0);
    }

    public boolean writeFormCheck(WriteForm form){
        if(form.getContent() == null || form.getTitle() == null
                || form.getContent().isEmpty() || form.getTitle().isEmpty()
                || form.getContent().isBlank() || form.getTitle().isBlank()){
            return false;
        }
        return true;
    }


    /**
     * 일단 단방향으로만 저장했는데, 유저에서 게시글 정보 참조할 때, 잘 가져올지는 의문.
     * 과제 제출 후 추가 학습때 유저 정보 조회 기능 구현할때 확인해볼 예정
     */
    public void uploadWriteContent(WriteForm writeForm, Member member){
        MemberPost memberPost = MemberPost.builder()
                .postMember(member)
                .postContent(writeForm.getContent())
                .postTitle(writeForm.getTitle())
                .postView(0)
                .postNumber((int)memberPostRepository.count()+1)
                .build();
        memberPostRepository.save(memberPost);
    }


}
