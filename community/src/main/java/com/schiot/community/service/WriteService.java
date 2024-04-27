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

    private final MemberPostRepository memberPostRepository;
    private final MemberCommentRepository memberCommentRepository;

    public boolean writeFormCheck(WriteForm form){
        if(form.getContent() == null || form.getTitle() == null
                || form.getContent().isBlank() || form.getTitle().isBlank()){
            return false;
        }
        return true;
    }

    public boolean updateFormCheck(UpdateForm form){
        if(form.getContent() == null || form.getTitle() == null
                || form.getContent().isBlank() || form.getTitle().isBlank()){
            return false;
        }
        return true;
    }



    public void uploadWriteContent(WriteForm writeForm, Member member){
        List<MemberPost> memberPosts = memberPostRepository.findAll();
        int big = 0;
        for (int i = 0; i < memberPosts.size(); i++) {
            big = (int)Math.max(big, memberPosts.get(i).getId());
        }

        MemberPost memberPost = MemberPost.builder()
                .postMember(member)
                .postContent(writeForm.getContent())
                .postTitle(writeForm.getTitle())
                .postView(0)
                .postNumber(big+1)
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
