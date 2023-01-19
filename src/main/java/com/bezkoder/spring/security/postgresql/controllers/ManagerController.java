package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.dtos.ManagerDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ManagerController {
    /*private BankAccountService bankAccountService;

    @GetMapping("/managers/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/managers/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/managers")
    public List<ManagerDto> managers() {
        return bankAccountService.listManagers();
    }

    @GetMapping("/managers/search")
    public List<ManagerDto> searchManagers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return bankAccountService.searchManagers("%" + keyword + "%");
    }

    @GetMapping("/managers/{id}")
    public ManagerDto getManager(@PathVariable(name = "id") Long managerId) throws ManagerNotFoundException {
        return bankAccountService.getManager(managerId);
    }

    @PostMapping("/managers")
    public ManagerDto saveManager(@RequestBody ManagerDto managerDto) {
        return bankAccountService.saveManager(managerDto);
    }

    @PutMapping("/managers/{managerId}")
    public ManagerDto updateManager(@PathVariable Long managerId, @RequestBody ManagerDto managerDto) {
        managerDto.setId(managerId);
        return bankAccountService.updateManager(managerDto);
    }

    @DeleteMapping("/managers/{id}")
    public void deleteManager(@PathVariable Long id) {
        bankAccountService.deleteManager(id);
    }*/

}
