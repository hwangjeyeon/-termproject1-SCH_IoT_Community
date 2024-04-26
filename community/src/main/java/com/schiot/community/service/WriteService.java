package com.schiot.community.service;

import com.schiot.community.entity.Member;
import com.schiot.community.entity.MemberComment;
import com.schiot.community.entity.MemberPost;
import com.schiot.community.form.CommentForm;
import com.schiot.community.form.UpdateForm;
import com.schiot.community.form.WriteForm;
import com.schiot.community.repository.MemberCommentRepository;
import com.schiot.community.repository.MemberPostRepository;
import com.schiot.community.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 게시글 CRUD 서비스 (업로드, 삭제, 수정, 검증 등)
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class WriteService {

    private final MemberRepository memberRepository;
    private final MemberPostRepository memberPostRepository;
    private final MemberCommentRepository memberCommentRepository;

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

    @Transactional
    public void updateWriteContent(UpdateForm updateForm, Integer postNumber, String studentId){
        List<MemberPost> posts = memberPostRepository.findByPostNumberAndPostMember_StudentId(postNumber, studentId);
        MemberPost memberPost = posts.get(0);
        memberPost.updateTitleAndContent(updateForm.getTitle(), updateForm.getContent());
    }

    public boolean commentFormCheck(CommentForm form){
        if(form.getContent() == null
                || form.getContent().isEmpty()
                || form.getContent().isBlank()){
            return false;
        }
        return true;
    }

    public void uploadComment(CommentForm form, MemberPost memberPost, Member member){
        MemberComment memberComment = MemberComment.builder()
                .commentsMember(member)
                .commentsPost(memberPost)
                .commentContent(form.getContent())
                .build();
        log.info("{}", memberComment.getCommentContent());
        memberCommentRepository.save(memberComment);
    }

    public void deleteContent(Integer postNumber, String studentId){
        List<MemberPost> memberPost = memberPostRepository.findByPostNumberAndPostMember_StudentId(postNumber, studentId);
        memberPostRepository.delete(memberPost.get(0));
    }

    public void deleteComment(Integer postNumber, Long commentId){
        List<MemberComment> postComment = memberCommentRepository.findByCommentsPost_PostNumberAndId(postNumber, commentId);
        log.info("{} ??", postComment.get(0));
        memberCommentRepository.delete(postComment.get(0));
    }


}
