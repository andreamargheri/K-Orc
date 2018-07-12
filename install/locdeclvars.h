// locdeclvars.h

// to store information about local declare vars and their scope

#ifndef _LOCDECLVARS_H
#define _LOCDECLVARS_H

#ifndef _GENERICSTACK_H
#include "genericstack.h"
#endif

#ifndef _DECLVAR_H
#include "declaredvars.h"
#endif

typedef GenericStack<DeclaredVars> declvar_stack_type;

typedef declvar_stack_type::stack_iterator LocalDeclaredVarsIterator ;

class LocalDeclaredVars
{
 protected:
  declvar_stack_type *varstack ;

  Variable *_findVar(DeclaredVars *i, WithId *j) const;

 public:
  LocalDeclaredVars() ;
  ~LocalDeclaredVars() ;

  void addScope() ;
  void removeScope() ;
  DeclaredVars *recentScope() const;
  void addVarToScope(Variable *) ;

  Variable *findVar( WithId *i ) const ;
} ;

#endif // _LOCDECLVARS_H
